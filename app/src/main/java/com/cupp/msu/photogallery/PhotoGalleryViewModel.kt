package com.cupp.msu.photogallery

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cupp.msu.photogallery.api.GalleryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "PhotoGalleryViewModel"

class PhotoGalleryViewModel : ViewModel() {
    private val photoRepository = PhotoRepository()

    private val _galleryItems: MutableStateFlow<List<GalleryItem>> =
        MutableStateFlow(emptyList())
    val galleryItems: StateFlow<List<GalleryItem>>
        get() = _galleryItems.asStateFlow()

    fun loadPhotosForDate(apiKey: String, date: String) {
        viewModelScope.launch {
            try {
                val items = photoRepository.fetchPhotosForDate(apiKey)
                _galleryItems.value = items
            } catch (ex: Exception) {
                Log.e("PhotoGalleryViewModel", "Failed to fetch date", ex)
            }
        }
    }

    fun loadPhotosForDateRange(apiKey: String, startDate: String, endDate: String) {
        viewModelScope.launch {
            try {
                val items = photoRepository.fetchPhotosForDateRange(apiKey, startDate)
                _galleryItems.value = items
            } catch (ex: Exception) {
                Log.e("PhotoGalleryViewModel", "Failed to fetch date range", ex)
            }
        }
    }

    fun loadRandomPhotos(apiKey: String, count: Int) {
        viewModelScope.launch {
            try {
                val items = photoRepository.fetchRandomPhotos(apiKey, count)
                _galleryItems.value = items
            } catch (ex: Exception) {
                Log.e("PhotoGalleryViewModel", "Failed to fetch random photos", ex)
            }
        }
    }

    fun loadPhotosWithThumbnails(apiKey: String, date: String, thumbs: Boolean) {
        viewModelScope.launch {
            try {
                val items = photoRepository.fetchThumbnails(apiKey, thumbs)
                _galleryItems.value = items
            } catch (ex: Exception) {
                Log.e("PhotoGalleryViewModel", "Failed to fetch thumbnails", ex)
            }
        }
    }
}
