package com.ingridelreal.itunessearchapp.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.ingridelreal.itunessearchapp.model.Album
import android.arch.lifecycle.LiveData
import android.content.ContentValues.TAG
import android.util.Log
import com.ingridelreal.itunessearchapp.data.remote.APIService
import com.ingridelreal.itunessearchapp.data.remote.ApiUtils
import com.ingridelreal.itunessearchapp.model.Artist
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ArtistViewModel: ViewModel() {
    private var albumsList: MutableLiveData<List<Album>> = MutableLiveData()

    private var mAPIService: APIService? = null

    init {
        mAPIService = ApiUtils.apiService
    }

    fun getAlbunsList(artistName : String?, entity : String = "album"): LiveData<List<Album>> {

        if (albumsList == null) {
            albumsList = MutableLiveData()
        }
        if (artistName != null)
            loadAlbums(artistName, "album")
        return albumsList
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "on cleared called")
    }

    fun loadAlbums(artistName : String, entity : String = "album") {
        mAPIService?.getAlbumsForArtist(artistName, entity)?.enqueue(object : Callback<Artist> {
            override fun onResponse(call: Call<Artist>?, response: Response<Artist>?) {
                if (response?.isSuccessful() == true){
                    albumsList?.setValue(response.body().albums)
                    Log.d("SearchAppActivity", "albums loaded from API")
                }
                else {
                    var status = response?.code()
                    //TODO: handle errors
                    Log.d("SearchAppActivity", " error %s "+status)
                }
            }

            override fun onFailure(call: Call<Artist>?, t: Throwable?) {
                //TODO: show error message
            }
        })
    }

}
