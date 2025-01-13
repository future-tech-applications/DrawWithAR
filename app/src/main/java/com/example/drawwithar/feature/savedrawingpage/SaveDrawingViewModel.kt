package com.example.drawwithar.feature.savedrawingpage

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drawwithar.feature.drawingpage.EMPTY_IMAGE_URI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveDrawingViewModel @Inject constructor() : ViewModel() {

    // State for image URI
    private val _previewImageUri = MutableStateFlow<Any>(EMPTY_IMAGE_URI)
    val previewImageUri: StateFlow<Any> get() = _previewImageUri

    // State for whether drawing is being reviewed to save
    private val _isDrawingBeingReviewed = MutableStateFlow(false)
    val isDrawingBeingReviewed: StateFlow<Boolean> get() = _isDrawingBeingReviewed


    // Logic for toggling opening gallery
    fun toggleIsDrawingBeingReviewed() {
        viewModelScope.launch {
            _isDrawingBeingReviewed.value = !_isDrawingBeingReviewed.value
            Log.d("Savedrawingviewmodle", "review state: ${isDrawingBeingReviewed.value}")
        }
    }

    // Save image in gallery
    fun updatePreviewImageUri(uri: Uri) {
        Log.d("SharedViewModel", "Selected image URI: $uri")
        viewModelScope.launch {
            _previewImageUri.emit(uri)
            if (uri != EMPTY_IMAGE_URI) {
                //_isStartDrawing.emit(true)
            }
        }
    }
}
