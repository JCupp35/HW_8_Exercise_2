package com.cupp.msu.photogallery

import com.cupp.msu.photogallery.api.NasaAPI
import com.cupp.msu.photogallery.api.GalleryItem
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class PhotoRepository {
    private val nasaApi: NasaAPI
private val apiKey = "FcbzgEwCV42C1bVOCdwLHQNZ9SIHGH89Q89gec3v"
    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        nasaApi = retrofit.create(NasaAPI::class.java)
    }

    suspend fun fetchPhotosForDate(date: String): List<GalleryItem> =
        nasaApi.fetchPhotos(apiKey, date = date)

    suspend fun fetchPhotosForDateRange(startDate: String, endDate: String): List<GalleryItem> =
        nasaApi.fetchPhotos(apiKey, startDate = startDate, endDate = endDate)

    suspend fun fetchRandomPhotos(apiKey: String, count: Int): List<GalleryItem> =
        nasaApi.fetchPhotos(this.apiKey, count = count)

    suspend fun fetchThumbnails(date: String, thumbs: Boolean): List<GalleryItem> =
        nasaApi.fetchPhotos(apiKey, date = date, thumbs = thumbs)
}
