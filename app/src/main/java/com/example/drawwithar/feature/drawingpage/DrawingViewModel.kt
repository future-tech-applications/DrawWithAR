package com.example.drawwithar.feature.drawingpage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.net.Uri
import com.example.drawwithar.core.common.Const
import com.example.drawwithar.core.common.model.BottomBarItemModel
import com.example.drawwithar.core.eventbus.AppEvent
import com.example.drawwithar.core.eventbus.AppEventBus
import com.example.drawwithar.feature.drawingpage.model.DrawingImageOrientation
import com.example.drawwithar.feature.drawingpage.uicomponent.getListOfBottomControlItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

val EMPTY_IMAGE_URI: Uri = Uri.parse("file://dev/null")

@HiltViewModel
class DrawingViewModel @Inject constructor(): ViewModel() {

    @Inject
    lateinit var appEventBus: AppEventBus


    private val _drawingControlItemSelectedStates = MutableStateFlow<List<Boolean>>(emptyList())
    val drawingControlItemSelectedStates: StateFlow<List<Boolean>> = _drawingControlItemSelectedStates.asStateFlow()

    private val _drawingControlItems = MutableStateFlow(getListOfBottomControlItems())
    val drawingControlItems: StateFlow<List<BottomBarItemModel>> = _drawingControlItems.asStateFlow()


    private val _drawingImageOrientation = MutableStateFlow(DrawingImageOrientation.NORMAL)
    val drawingImageOrientation: StateFlow<DrawingImageOrientation> = _drawingImageOrientation


    private val _scale = MutableStateFlow(Const.Scale.INITIAL_SCALE)
    val scale: StateFlow<Float> = _scale

    private val _rotation = MutableStateFlow(Const.Rotation.INITIAL_ROTATION)
    val rotation: StateFlow<Float> = _rotation

    private val _offsetX = MutableStateFlow(Const.Offset.INITIAL_OFFSET_X)
    val offsetX: StateFlow<Float> = _offsetX

    private val _offsetY = MutableStateFlow(Const.Offset.INITIAL_OFFSET_Y)
    val offsetY: StateFlow<Float> = _offsetY

    fun updateBottomControlItems(newItems: List<BottomBarItemModel>) {
        _drawingControlItems.value = newItems
    }

    fun updateDrawingImageOrientation(newOrientation: DrawingImageOrientation) {
        _drawingImageOrientation.value = newOrientation
    }

    fun updateScale(newScale: Float) {
        _scale.value = newScale.coerceIn(Const.Scale.MIN_SCALE, Const.Scale.MAX_SCALE)
    }

    fun updateRotation(newRotation: Float) {
        _rotation.value = newRotation
    }

    fun updateOffset(newOffsetX: Float, newOffsetY: Float) {
        _offsetX.value = newOffsetX.coerceIn(Const.Offset.MIN_OFFSET_X, Const.Offset.MAX_OFFSET_X)
        _offsetY.value = newOffsetY.coerceIn(Const.Offset.MIN_OFFSET_Y, Const.Offset.MAX_OFFSET_Y)
    }


    private val _isFlashlightToggled = MutableStateFlow(false)
    val isFlashlightToggled: StateFlow<Boolean> = _isFlashlightToggled


    // Tracks whether the canvas is frozen
    private val _isDrawingImageFrozen = MutableStateFlow(false)
    val isDrawingImageFrozen: StateFlow<Boolean> = _isDrawingImageFrozen

    private fun toggleFreezeState() {
        _isDrawingImageFrozen.value = !_isDrawingImageFrozen.value
    }

    private fun toggleOpacitySlider() {
        _isOpacitySliderVisible.value = !_isOpacitySliderVisible.value
    }

    private fun toggleFlashlight() {
        viewModelScope.launch {
            _isFlashlightToggled.value = !_isFlashlightToggled.value
            appEventBus.emit(AppEvent.FlashLightToggledEvent(isFlashlightToggled.value))
        }
    }

    suspend fun initializeDrawingControlItemStates(size: Int) {
        if (_drawingControlItemSelectedStates.value.isEmpty()) { // Ensure only initialized once
            _drawingControlItemSelectedStates.value = List(size) { index -> index == 0 } // 0th index true
            _selectedDrawingControlItem.emit(0)
        }
    }

    fun onDrawingControlItemSelected(index: Int) {
        viewModelScope.launch {
            _selectedDrawingControlItem.emit(index)
            updateDrawingControlItemStateOnSelected(index)
            handleDrawingControlItemOnSelected(index)
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
    private val _alphaValue = MutableStateFlow(Const.OpacitySlider.INITIAL_VALUE)
    val alphaValue: StateFlow<Float> = _alphaValue

//    // Bottom navigation selected tab
    private val _selectedDrawingControlItem = MutableStateFlow(0)
    val selectedDrawingControlItem: StateFlow<Int> = _selectedDrawingControlItem

    // Visibility of the opacity slider
    private val _isOpacitySliderVisible = MutableStateFlow(Const.OpacitySlider.IS_VISIBLE)
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
    private fun handleDrawingControlItemOnSelected(selectedItemIndex: Int) {
        val isCurrentlySelected = drawingControlItemSelectedStates.value[selectedItemIndex]
        viewModelScope.launch {
            when (selectedItemIndex) {
                0 -> {
                    toggleOpacitySlider()
                }
                1 -> {
                    handleFlipAction()
                }
                2 -> {
                    toggleFlashlight()
                }
                3 -> {
                    toggleFreezeState()
                }
                4 -> {
                    resetDrawingImageStates()
                }


            }
        }
    }



    private fun handleFlipAction() {
        _drawingImageOrientation.value = when (_drawingImageOrientation.value) {
            DrawingImageOrientation.NORMAL -> DrawingImageOrientation.FLIPPED_HORIZONTAL
            DrawingImageOrientation.FLIPPED_HORIZONTAL -> DrawingImageOrientation.FLIPPED_VERTICAL
            DrawingImageOrientation.FLIPPED_VERTICAL -> DrawingImageOrientation.NORMAL
        }
    }


    // Logic for selecting a tab
    private fun updateDrawingControlItemStateOnSelected(selectedItemIndex: Int) {
        viewModelScope.launch {
            val newStates = _drawingControlItemSelectedStates.value.toMutableList()
            newStates[selectedItemIndex] = !newStates[selectedItemIndex]
            _drawingControlItemSelectedStates.value = newStates
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

    fun resetDrawingImageStates() {
        _scale.value = Const.Scale.INITIAL_SCALE
        _rotation.value = Const.Rotation.INITIAL_ROTATION
        _offsetX.value = Const.Offset.INITIAL_OFFSET_X
        _offsetY.value = Const.Offset.INITIAL_OFFSET_Y
        _drawingImageOrientation.value = DrawingImageOrientation.NORMAL
        _isOpacitySliderVisible.value = false
        _alphaValue.value = Const.OpacitySlider.INITIAL_VALUE
        _isDrawingImageFrozen.value = false
        if(isFlashlightToggled.value) {
            toggleFlashlight()
        }
        _drawingControlItemSelectedStates.value =
            List(drawingControlItems.value.size) { index -> index == -1 }

    }
}
