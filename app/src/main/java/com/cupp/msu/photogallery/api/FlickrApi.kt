package com.cupp.msu.photogallery.api

import retrofit2.http.GET

private const val API_KEY = "04dbb81bea99d89146da78a9eb0bc3b5"

interface FlickrApi {
    @GET(
        "services/rest/?method=flickr.interestingness.getList" +
            "&api_key=$API_KEY" +
            "&format=json" +
            "&nojsoncallback=1" +
            "&extras=url_s"
    )
    suspend fun fetchPhotos(): FlickrResponse
}
