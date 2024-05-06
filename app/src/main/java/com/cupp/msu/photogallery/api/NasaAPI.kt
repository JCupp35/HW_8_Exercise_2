package com.cupp.msu.photogallery.api

import retrofit2.http.GET
import retrofit2.http.Query


interface NasaAPI {
    @GET(
        "/planetary/apod")
    suspend fun fetchPhotos(
        @Query("date") date: String? = null,
        @Query("start_date") startDate: String? = null,
        @Query("end_date") endDate: String? = null,
        @Query("count") count: Int? = null,
        @Query("thumbs") thumbs: Boolean? = null,
        @Query("api_key") apiKey: String): List<GalleryItem>
}
