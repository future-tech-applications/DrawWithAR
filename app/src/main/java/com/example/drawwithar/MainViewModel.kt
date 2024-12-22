package com.example.drawwithar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.net.Uri
import kotlinx.coroutines.flow.asStateFlow

val EMPTY_IMAGE_URI: Uri = Uri.parse("file://dev/null")

class MainViewModel : ViewModel() {

    private val _drawingControlItemStates = MutableStateFlow<List<Boolean>>(emptyList())
    val drawingControlItemStates: StateFlow<List<Boolean>> = _drawingControlItemStates.asStateFlow()

    suspend fun initializeDrawingControlItemStates(size: Int) {
        if (_drawingControlItemStates.value.isEmpty()) { // Ensure only initialized once
            _drawingControlItemStates.value = List(size) { index -> index == 0 } // 0th index true
            _selectedDrawingControlItem.emit(0)
        }
    }

    fun onDrawingControlItemSelected(index: Int) {
        viewModelScope.launch {
            _selectedDrawingControlItem.emit(index)
            updateDrawingControlItemState(index)
            handleDrawingControlItemSelected(index)
        }
    }

    // state for if gallery open
    private val _showGallery = MutableStateFlow(false)
    val showGallery: StateFlow<Boolean> = _showGallery

    // state for if image chosen or captured
    private val _imageUri = MutableStateFlow(EMPTY_IMAGE_URI)
    val imageUri: StateFlow<Uri> = _imageUri

    // state for if drawing started (an image choosen or captured and drawing started)
    private val _isStartDrawing = MutableStateFlow(false)
    val isStartDrawing: StateFlow<Boolean> = _isStartDrawing

    // Alpha value for opacity slider
    private val _alphaValue = MutableStateFlow(0.5f)
    val alphaValue: StateFlow<Float> = _alphaValue

//    // Bottom navigation selected tab
    private val _selectedDrawingControlItem = MutableStateFlow(0)
    val selectedDrawingControlItem: StateFlow<Int> = _selectedDrawingControlItem

    // Visibility of the opacity slider
    private val _isOpacitySliderVisible = MutableStateFlow(true)
    val isOpacitySliderVisible: StateFlow<Boolean> = _isOpacitySliderVisible

    // Logic for toggling drawing state (drawing started)
    fun toggleDrawing() {
        viewModelScope.launch {
            if (_isStartDrawing.value) {
                _imageUri.emit(EMPTY_IMAGE_URI)
            }
            _isStartDrawing.emit(!_isStartDrawing.value)
        }
    }

    // Logic for updating alpha value
    fun updateAlphaValue(alpha: Float) {
        viewModelScope.launch {
            _alphaValue.emit(alpha)
        }
    }

    // Logic to handle when a drawing control item selected
    private fun handleDrawingControlItemSelected(selectedItemIndex: Int) {
        val isCurrentlySelected = drawingControlItemStates.value[selectedItemIndex]
        viewModelScope.launch {
            when (selectedItemIndex) {
                0 -> {
                    _isOpacitySliderVisible.emit(!isCurrentlySelected)
                }
            }
        }
    }

    // Logic for selecting a tab
    private fun updateDrawingControlItemState(selectedItemIndex: Int) {
        viewModelScope.launch {
            val newStates = _drawingControlItemStates.value.toMutableList()
            newStates[selectedItemIndex] = !newStates[selectedItemIndex]
            _drawingControlItemStates.value = newStates
        }
    }

    // Logic for toggling opening gallery
    fun toggleShowGallery() {
        viewModelScope.launch {
            _showGallery.value = !_showGallery.value
        }
    }

    // Logic for selecting an image (from gallery)
    fun selectImage(uri: Uri) {
        viewModelScope.launch {
            _imageUri.emit(uri)
            if (uri != EMPTY_IMAGE_URI) {
                _isStartDrawing.emit(true)
            }
        }
    }
}
