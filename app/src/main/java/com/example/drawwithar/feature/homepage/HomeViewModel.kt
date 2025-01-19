package com.example.drawwithar.feature.homepage

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drawwithar.room.DrawingDao
import com.example.drawwithar.room.DrawingEntity
import com.example.drawwithar.util.ImageUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dao: DrawingDao
): ViewModel() {

    private val _selectedImage = MutableStateFlow<Any?>(null)
    val selectedImage: StateFlow<Any?> = _selectedImage.asStateFlow()

    private var _savedDrawingsList = MutableStateFlow<List<Uri>>(emptyList())
    val savedDrawingsList: StateFlow<List<Uri>> = _savedDrawingsList.asStateFlow()

    private var _favoriteDrawingsList = MutableStateFlow<List<Uri>>(emptyList())
    val favoriteDrawingsList: StateFlow<List<Uri>> = _favoriteDrawingsList.asStateFlow()




    fun insertDrawing(drawing: DrawingEntity) {
        viewModelScope.launch {
            dao.insertDrawing(drawing)
        }
    }

    fun deleteDrawing(drawing: DrawingEntity) {
        viewModelScope.launch {
            dao.deleteDrawing(drawing)
        }
    }

    fun fetchSavedImages(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val fetchedDrawings = ImageUtils.fetchSavedImages(context)
            _savedDrawingsList.emit(fetchedDrawings)
        }
    }

    fun fetchFavoriteImages() {
        viewModelScope.launch(Dispatchers.IO) {
            val favorites = dao.getFavorites()
            val filterUris = favorites.map { Uri.parse(it.uri) }
            _favoriteDrawingsList.emit(filterUris)
        }
    }
}