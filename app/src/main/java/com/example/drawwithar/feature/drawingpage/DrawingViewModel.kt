package com.example.drawwithar.feature.drawingpage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.net.Uri
import com.example.drawwithar.R
import com.example.drawwithar.core.common.Const
import com.example.drawwithar.core.common.model.BottomBarItemModel
import com.example.drawwithar.core.common.model.CustomToastData
import com.example.drawwithar.core.eventbus.AppEvent
import com.example.drawwithar.core.eventbus.AppEventBus
import com.example.drawwithar.feature.drawingpage.model.DrawingImageOrientation
import com.example.drawwithar.feature.drawingpage.uicomponent.getListOfBottomControlItems
import com.example.drawwithar.feature.drawingpage.uicomponent.getListOfFlipActionItems
import com.example.drawwithar.room.DrawingDao
import com.example.drawwithar.room.DrawingEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

val EMPTY_IMAGE_URI: Any = Uri.parse("file://dev/null")

val templates = listOf(
    R.drawable.drawing_template_1,
    R.drawable.drawing_template_2,
    R.drawable.drawing_template_3,
    R.drawable.drawing_template_4,
    R.drawable.drawing_template_5,
    R.drawable.drawing_template_6,
    R.drawable.drawing_template_7,
    R.drawable.drawing_template_8,
    R.drawable.drawing_template_aladdin_4,
    R.drawable.drawing_template_barbie_3,
    R.drawable.drawing_template_black_panther_avt_2,
    R.drawable.drawing_template_black_panther_body,
    R.drawable.drawing_template_dog_or_rabit_3,
    R.drawable.drawing_template_ghost_cute,
    R.drawable.drawing_template_girl_an2,
    R.drawable.drawing_template_goofy_simple,
    R.drawable.drawing_template_happy_cat_dgsl,
    R.drawable.drawing_template_happy_cat_dgsl2,
    R.drawable.drawing_template_jerry_drawi_2,
    R.drawable.drawing_template_kakashi_dz,
    R.drawable.drawing_template_kuromi,
    R.drawable.drawing_template_lightning_mcqueen_3,
    R.drawable.drawing_template_loa_2,
    R.drawable.drawing_template_wooddy4,
    R.drawable.drawing_template_tinker_bell,
    R.drawable.drawing_template_the_little_mermaid,
    R.drawable.drawing_template_pumpkins_cute,
    R.drawable.drawing_template_nobita_3,
    R.drawable.drawing_template_spiderman_chibi,
    R.drawable.drawing_template_snoopy_1,
    R.drawable.drawing_template_snoopy_2,
    R.drawable.drawing_template_spidermankzq,
    R.drawable.drawing_template_thma_2,
    R.drawable.drawing_template_tigg4,
    R.drawable.drawing_template_tigger_al,
    R.drawable.drawing_template_50,
    R.drawable.drawing_template_pooh_2,
    R.drawable.drawing_template_rabbit_2,
    R.drawable.drawing_template_lightning_mcqueen_4_splash_art,
    R.drawable.drawing_template_snoop_6,

)

@HiltViewModel
class DrawingViewModel @Inject constructor(): ViewModel() {

    // for observing reactive events globally
    @Inject
    lateinit var appEventBus: AppEventBus

    // state of if drawing image has any control applied on it
    private val _hasChanges = MutableStateFlow(false)
    val hasChanges: StateFlow<Boolean> = _hasChanges

    // list of drawing control items to control drawing image
    private val _drawingControlItems = MutableStateFlow(getListOfBottomControlItems())
    val drawingControlItems: StateFlow<List<BottomBarItemModel>> = _drawingControlItems.asStateFlow()

    // list of states of drawing control items
    private val _drawingControlItemSelectedStates = MutableStateFlow<List<Boolean>>(emptyList())
    val drawingControlItemSelectedStates: StateFlow<List<Boolean>> = _drawingControlItemSelectedStates.asStateFlow()

    // list of flip control items to control drawing image flips
    private val _flipControlItems = MutableStateFlow(getListOfFlipActionItems())
    val flipControlItems: StateFlow<List<BottomBarItemModel>> = _flipControlItems.asStateFlow()

    // list of states for flip control items
    private val _flipControlItemSelectedStates = MutableStateFlow<List<Boolean>>(emptyList())
    val flipControlItemSelectedStates: StateFlow<List<Boolean>> = _flipControlItemSelectedStates.asStateFlow()

    // state of Orientation of drawing image
    private val _drawingImageOrientation = MutableStateFlow(DrawingImageOrientation.NORMAL)
    val drawingImageOrientation: StateFlow<DrawingImageOrientation> = _drawingImageOrientation

    // state of drawing image scale/zoom
    private val _scale = MutableStateFlow(Const.Scale.INITIAL_SCALE)
    val scale: StateFlow<Float> = _scale

    // state of drawing image rotation
    private val _rotation = MutableStateFlow(Const.Rotation.INITIAL_ROTATION)
    val rotation: StateFlow<Float> = _rotation

    // state of drawing image horizontal position
    private val _offsetX = MutableStateFlow(Const.Offset.INITIAL_OFFSET_X)
    val offsetX: StateFlow<Float> = _offsetX

    // state of drawing image vertical position
    private val _offsetY = MutableStateFlow(Const.Offset.INITIAL_OFFSET_Y)
    val offsetY: StateFlow<Float> = _offsetY

    // state of if flash is toggled
    private val _isFlashlightToggled = MutableStateFlow(false)
    private val isFlashlightToggled: StateFlow<Boolean> = _isFlashlightToggled

    // state of if drawing image frozen or not
    private val _isDrawingImageFrozen = MutableStateFlow(false)
    val isDrawingImageFrozen: StateFlow<Boolean> = _isDrawingImageFrozen

    // Alpha value for opacity slider
    private val _alphaValue = MutableStateFlow(Const.OpacitySlider.INITIAL_VALUE)
    val alphaValue: StateFlow<Float> = _alphaValue

    // bottom navigation selected tab
    private val _selectedDrawingControlItem = MutableStateFlow(0)
    val selectedDrawingControlItem: StateFlow<Int> = _selectedDrawingControlItem

    // flip item selected state
    private val _selectedFlipControlItem = MutableStateFlow(0)
    val selectedFlipControlItem: StateFlow<Int> = _selectedFlipControlItem

    // visibility of the opacity slider
    private val _isOpacitySliderVisible = MutableStateFlow(Const.OpacitySlider.IS_VISIBLE)
    val isOpacitySliderVisible: StateFlow<Boolean> = _isOpacitySliderVisible

    // visibility of the flip action items
    private val _isFlipActionVisible = MutableStateFlow(Const.FlipAction.IS_VISIBLE)
    val isFlipActionVisible: StateFlow<Boolean> = _isFlipActionVisible

    // visibility of the opacity slider
    private val _isExitConfirmDialogOpened = MutableStateFlow(false)
    val isExitConfirmDialogOpened: StateFlow<Boolean> = _isExitConfirmDialogOpened

    // visibility of the opacity slider
    private val _isDrawingStateResetDialogOpened = MutableStateFlow(false)
    val isDrawingStateResetDialogOpened: StateFlow<Boolean> = _isDrawingStateResetDialogOpened

    // check if  drawing state has been reset
    private val _isDrawingStateReset = MutableStateFlow(false)
    val isDrawingStateReset: StateFlow<Boolean> = _isDrawingStateReset

    // visibility of the opacity slider
    private val _toastData = MutableStateFlow(CustomToastData())
    val toastData: StateFlow<CustomToastData> = _toastData



    fun updateExitConfirmDialogOpened(value: Boolean) {
        _isExitConfirmDialogOpened.value = value
    }

    fun updateResetDrawingConfirmDialogOpened(value: Boolean) {
        _isDrawingStateResetDialogOpened.value = value
    }

    private fun updateDrawingStateReset(value: Boolean) {
        _isDrawingStateReset.value = value
        if(isDrawingStateReset.value) {
            updateShowToast(
                CustomToastData(
                    isVisible = true,
                    message = "Changes has been reset",
                    onDismiss = {
                        updateShowToast(CustomToastData(isVisible = false))
                    }
                )
            )
        }
    }

    private fun updateShowToast(toastData: CustomToastData) {
        _toastData.value = toastData
    }

    // initial states of drawing control items
    suspend fun initializeDrawingControlItemStates(size: Int) {
        if (_drawingControlItemSelectedStates.value.isEmpty()) { // Ensure only initialized once
            _drawingControlItemSelectedStates.value = List(size) { index -> index == 0 } // 0th index true
            _selectedDrawingControlItem.emit(0)
        }
    }

    // initial states of flip items for drawing image
    suspend fun initializeFlipControlItemStates(size: Int) {
        if (_flipControlItemSelectedStates.value.isEmpty()) {
            _flipControlItemSelectedStates.value = List(size) { index -> index == -1 } // none selected
        }
    }

    // update drawing image scale/zoom
    fun updateScale(newScale: Float) {
        _hasChanges.value = true
        _scale.value = newScale.coerceIn(Const.Scale.MIN_SCALE, Const.Scale.MAX_SCALE)
    }

    // update drawing image rotation
    fun updateRotation(newRotation: Float) {
        _hasChanges.value = true
        _rotation.value = newRotation
    }

    // update drawing image position
    fun updateOffset(newOffsetX: Float, newOffsetY: Float) {
        _hasChanges.value = true
        _offsetX.value = newOffsetX.coerceIn(Const.Offset.MIN_OFFSET_X, Const.Offset.MAX_OFFSET_X)
        _offsetY.value = newOffsetY.coerceIn(Const.Offset.MIN_OFFSET_Y, Const.Offset.MAX_OFFSET_Y)
    }

    // toggle drawing image lock/unlock movement
    private fun toggleFreezeState() {
        _hasChanges.value = true
        _isDrawingImageFrozen.value = !_isDrawingImageFrozen.value
        updateShowToast(
            CustomToastData(
                isVisible = true,
                message = if (_isDrawingImageFrozen.value) "Drawing Image Locked" else "Drawing Image Unlocked",
                onDismiss = {
                    updateShowToast(CustomToastData(isVisible = false))
                }

            )
        )
    }

    // toggle drawing image opacity slider control visibility
    private fun toggleOpacitySlider() {
        _isOpacitySliderVisible.value = !_isOpacitySliderVisible.value
        if(isOpacitySliderVisible.value) {
            updateShowToast(
                CustomToastData(
                    isVisible = true,
                    message = "Slide to adjust Opacity",
                    onDismiss = {
                        updateShowToast(CustomToastData(isVisible = false))
                    }
                )
            )
        }

    }

    // toggle to popup/hide the drawing image flip actions
    private fun toggleFlipAction() {
        _isFlipActionVisible.value = !_isFlipActionVisible.value
    }

    // toggle flashlight when drawing
    private fun toggleFlashlight() {
        _hasChanges.value = true
        viewModelScope.launch {
            _isFlashlightToggled.value = !_isFlashlightToggled.value
            appEventBus.emit(AppEvent.FlashLightToggledEvent(isFlashlightToggled.value))
            updateShowToast(
                CustomToastData(
                    isVisible = true,
                    message = if (isFlashlightToggled.value) "Flashlight ON" else "Flashlight OFF",
                    onDismiss = {
                        updateShowToast(CustomToastData(isVisible = false))
                    }
                )
            )
        }

    }

    // handle when a drawing control item selected
    fun onDrawingControlItemSelected(index: Int) {
        viewModelScope.launch {
            _selectedDrawingControlItem.emit(index)
            updateDrawingControlItemStateOnSelected(index)
            handleDrawingControlItemOnSelected(index)
        }
    }

    // handle when a flip drawing image item selected
    fun onFlipControlItemSelected(index: Int) {
        _hasChanges.value = true
        viewModelScope.launch {
            _selectedFlipControlItem.emit(index)
            updateFlipControlItemStateOnSelected(index)
            handleFlipControlItemOnSelected(index)
        }
    }


    // Logic for updating alpha value
    fun updateAlphaValue(alpha: Float) {
        _hasChanges.value = true
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
                    toggleFlipAction()
                }
                2 -> {
                    toggleFlashlight()
                }
                3 -> {
                    toggleFreezeState()
                }
                4 -> {
                    updateResetDrawingConfirmDialogOpened(true)
                }
            }
        }
    }

    // Logic to handle flip control item selected
    private fun handleFlipControlItemOnSelected(selectedItemIndex: Int) {
        val isCurrentlySelected = flipControlItemSelectedStates.value[selectedItemIndex]
        viewModelScope.launch {
            when (selectedItemIndex) {
                0 -> {
                    handleHorizontalFlipAction()
                }
                1 -> {
                    handleVerticalFlipAction()
                }
            }
        }
    }

    // handle horizontal flip
    private fun handleHorizontalFlipAction() {
        _drawingImageOrientation.value = when (_drawingImageOrientation.value) {
            DrawingImageOrientation.FLIPPED_HORIZONTAL -> DrawingImageOrientation.NORMAL
            DrawingImageOrientation.FLIPPED_VERTICAL -> DrawingImageOrientation.FLIPPED_BOTH
            DrawingImageOrientation.FLIPPED_BOTH -> DrawingImageOrientation.FLIPPED_VERTICAL
            else -> DrawingImageOrientation.FLIPPED_HORIZONTAL
        }
        updateShowToast(
            CustomToastData(
                isVisible = true,
                message = "Image flipped Horizontally",
                onDismiss = {
                    updateShowToast(CustomToastData(isVisible = false))
                }
            )
        )
    }

    // handle vertical flip
    private fun handleVerticalFlipAction() {
        _drawingImageOrientation.value = when (_drawingImageOrientation.value) {
            DrawingImageOrientation.FLIPPED_VERTICAL -> DrawingImageOrientation.NORMAL
            DrawingImageOrientation.FLIPPED_HORIZONTAL -> DrawingImageOrientation.FLIPPED_BOTH
            DrawingImageOrientation.FLIPPED_BOTH -> DrawingImageOrientation.FLIPPED_HORIZONTAL
            else -> DrawingImageOrientation.FLIPPED_VERTICAL
        }
        updateShowToast(
            CustomToastData(
                isVisible = true,
                message = "Image flipped Vertically",
                onDismiss = {
                    updateShowToast(CustomToastData(isVisible = false))
                }
            )
        )
    }

    // Logic for selecting a tab
    private fun updateDrawingControlItemStateOnSelected(selectedItemIndex: Int) {
        viewModelScope.launch {
            val newStates = _drawingControlItemSelectedStates.value.toMutableList()
            newStates[selectedItemIndex] = !newStates[selectedItemIndex]
            _drawingControlItemSelectedStates.value = newStates
        }
    }

    // Logic for selecting a flip option
    private fun updateFlipControlItemStateOnSelected(selectedItemIndex: Int) {
        viewModelScope.launch {
            val newStates = _flipControlItemSelectedStates.value.toMutableList()
            newStates[selectedItemIndex] = !newStates[selectedItemIndex]
            _flipControlItemSelectedStates.value = newStates
        }
    }


    // reset all the applied controls on drawing image
    fun resetDrawingImageStates() {
        _scale.value = Const.Scale.INITIAL_SCALE
        _rotation.value = Const.Rotation.INITIAL_ROTATION
        _offsetX.value = Const.Offset.INITIAL_OFFSET_X
        _offsetY.value = Const.Offset.INITIAL_OFFSET_Y
        _drawingImageOrientation.value = DrawingImageOrientation.NORMAL
        _isOpacitySliderVisible.value = false
        _isFlipActionVisible.value = false
        _alphaValue.value = Const.OpacitySlider.INITIAL_VALUE
        _isDrawingImageFrozen.value = false
        if(isFlashlightToggled.value) {
            toggleFlashlight()
        }
        _drawingControlItemSelectedStates.value =
            List(drawingControlItems.value.size) { index -> index == -1 }
        _flipControlItemSelectedStates.value =
            List(flipControlItems.value.size) { index -> index == -1 }
        _hasChanges.value = false
        updateDrawingStateReset(true)
    }
}
