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
    // State for image URI
    private val _imageUri = MutableStateFlow<Any>(EMPTY_IMAGE_URI)
    val imageUri: StateFlow<Any> get() = _imageUri

    // State for whether drawing has started
    private val _isStartDrawing = MutableStateFlow(false)
    val isStartDrawing: StateFlow<Boolean> get() = _isStartDrawing

    // Toggle drawing state
    fun toggleDrawing() {
        viewModelScope.launch {
            if (_isStartDrawing.value) {
                _imageUri.emit(EMPTY_IMAGE_URI)
            }
            _isStartDrawing.emit(!_isStartDrawing.value)
            Log.d("SharedViewModel", "isStartDrawing value: ${isStartDrawing.value}")

        }

    }

    // Select an image
    fun selectImage(uri: Any) {
        Log.d("SharedViewModel", "Selected image URI: $uri")
        viewModelScope.launch {
            _imageUri.emit(uri)
            if (uri != EMPTY_IMAGE_URI) {
                _isStartDrawing.emit(true)
            }
        }
    }
}
