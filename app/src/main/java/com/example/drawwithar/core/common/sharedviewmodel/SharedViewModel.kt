package com.example.drawwithar.core.common.sharedviewmodel

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
class SharedViewModel @Inject constructor() : ViewModel() {
    // State for selected image for drawing
    private val _selectedImageSrcForDrawing = MutableStateFlow<Any>(EMPTY_IMAGE_URI)
    val selectedImageSrcForDrawing: StateFlow<Any> get() = _selectedImageSrcForDrawing

    // State for whether drawing has started
    private val _isStartDrawing = MutableStateFlow(false)
    val isStartDrawing: StateFlow<Boolean> get() = _isStartDrawing

    private val _showGallery = MutableStateFlow(false)
    val showGallery: StateFlow<Boolean> = _showGallery


    // Logic for toggling opening gallery
    fun toggleShowGallery() {
        viewModelScope.launch {
            _showGallery.value = !_showGallery.value
        }
    }

    // Toggle drawing state
    fun toggleDrawing() {
        viewModelScope.launch {
            if (_isStartDrawing.value) {
                _selectedImageSrcForDrawing.emit(EMPTY_IMAGE_URI)
            }
            _isStartDrawing.emit(!_isStartDrawing.value)
            Log.d("SharedViewModel", "isStartDrawing value: ${isStartDrawing.value}")

        }

    }

    // Select an image
    fun selectImageForDrawing(src: Any) {
        Log.d("SharedViewModel", "Selected image URI: $src")
        viewModelScope.launch {
            _selectedImageSrcForDrawing.emit(src)
            if (src != EMPTY_IMAGE_URI) {
                _isStartDrawing.emit(true)
            }
        }
    }
}
