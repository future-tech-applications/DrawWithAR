package com.example.drawwithar.feature.savedrawingpage

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drawwithar.feature.drawingpage.EMPTY_IMAGE_URI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

const val folderName: String = "DrawWithAR"

@HiltViewModel
class SaveDrawingViewModel @Inject constructor() : ViewModel() {

    // State for image URI
    private val _previewImageUri = MutableStateFlow<Any>(EMPTY_IMAGE_URI)
    val previewImageUri: StateFlow<Any> get() = _previewImageUri

    // State for whether drawing is being reviewed to save
    private val _isDrawingBeingReviewed = MutableStateFlow(false)
    val isDrawingBeingReviewed: StateFlow<Boolean> get() = _isDrawingBeingReviewed

    // State for saving status
    private val _isSaving = MutableStateFlow(false)
    val isSaving: StateFlow<Boolean> get() = _isSaving

    private val _saveResult = MutableStateFlow<Result<Unit>?>(null)
    val saveResult: StateFlow<Result<Unit>?> get() = _saveResult

    // Logic for toggling drawing review state
    fun toggleIsDrawingBeingReviewed() {
        viewModelScope.launch {
            _isDrawingBeingReviewed.value = !_isDrawingBeingReviewed.value
            Log.d("SaveDrawingViewModel", "Review state: ${isDrawingBeingReviewed.value}")
        }
    }

    // Update preview image URI
    fun updatePreviewImageUri(uri: Uri) {
        Log.d("SaveDrawingViewModel", "Selected image URI: $uri")
        viewModelScope.launch {
            _previewImageUri.emit(uri)
        }
    }

    // Save image to gallery
    fun saveImageToGallery(context: Context) {
        val imageUri = previewImageUri.value
        if (imageUri !is Uri || imageUri == EMPTY_IMAGE_URI) {
            _saveResult.value = Result.failure(IllegalArgumentException("No image selected"))
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            _isSaving.emit(true)
            try {
                val contentValues = ContentValues().apply {
                    val relativePath = "Pictures/$folderName/"
                    put(MediaStore.MediaColumns.DISPLAY_NAME, "draw_with_ar_drawing_${System.currentTimeMillis()}.jpg")
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        put(MediaStore.MediaColumns.RELATIVE_PATH, relativePath)
                        Log.d("SaveDrawingViewModel", "Saving to path: $relativePath")

                    }
                }

                val resolver = context.contentResolver
                val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

                if (uri != null) {
                    resolver.openOutputStream(uri)?.use { outputStream ->
                        resolver.openInputStream(imageUri)?.copyTo(outputStream)
                    }
                    _saveResult.emit(Result.success(Unit))
                    Log.d("SaveDrawingViewModel", "Image saved successfully")
                } else {
                    throw IOException("Failed to create MediaStore entry")
                }
            } catch (e: Exception) {
                Log.e("SaveDrawingViewModel", "Error saving image", e)
                _saveResult.emit(Result.failure(e))
            } finally {
                _isSaving.emit(false)
            }
        }
    }


}
