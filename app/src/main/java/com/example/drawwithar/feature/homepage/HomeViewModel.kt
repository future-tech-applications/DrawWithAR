package com.example.drawwithar.feature.homepage

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drawwithar.util.ImageUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject () constructor(): ViewModel() {

    private val _selectedImage = MutableStateFlow<Any?>(null)
    val selectedImage: StateFlow<Any?> = _selectedImage.asStateFlow()

    private var _savedDrawingsList = MutableStateFlow<List<Uri>>(emptyList())
    val savedDrawingsList: StateFlow<List<Uri>> = _savedDrawingsList.asStateFlow()

    fun fetchSavedImages(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val fetchedDrawings = ImageUtils.fetchSavedImages(context)
            _savedDrawingsList.emit(fetchedDrawings)
        }
    }
}