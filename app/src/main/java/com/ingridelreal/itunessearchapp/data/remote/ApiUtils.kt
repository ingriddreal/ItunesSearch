package com.ingridelreal.itunessearchapp.data.remote

object ApiUtils {

    const val BASE_URL = "https://itunes.apple.com"
    const val SEARCH_URL = "/search"
    const val ALBUMS_URL = "/lookup?"

    val apiService: APIService
        get() = RetrofitClient.getClient(BASE_URL)!!.create(APIService::class.java)
}