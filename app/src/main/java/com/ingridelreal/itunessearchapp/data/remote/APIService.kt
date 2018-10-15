package com.ingridelreal.itunessearchapp.data.remote

import com.ingridelreal.itunessearchapp.model.Album
import com.ingridelreal.itunessearchapp.model.Artist
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET(ApiUtils.SEARCH_URL)
    fun getAlbumsForArtist(@Query("term") artistName: String,
                  @Query("entity") entity: String): Call<Artist>


    @GET(ApiUtils.SEARCH_URL)
    fun getAlbums(@Query("term") artistName: String,
                  @Query("entity") entity: String): Call<List<Album>>
}