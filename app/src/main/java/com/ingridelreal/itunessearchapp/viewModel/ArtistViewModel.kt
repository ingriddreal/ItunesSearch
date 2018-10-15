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
import java.util.*


class ArtistViewModel: ViewModel() {
    private var albumsList: MutableLiveData<List<Album>> = MutableLiveData()

    private var mAPIService: APIService? = null

    init {
        mAPIService = ApiUtils.apiService
    }

    fun getAlbunsList(): LiveData<List<Album>> {
        if (albumsList == null) {
            albumsList = MutableLiveData()
            loadAlbums("taylor swift", "album")
        }
        else {
            loadAlbums("taylor swift", "album")
        }
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
                    //TODO: update recycler view with adapter
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

    //Test data
    fun loadAlbumsTest() {
        val newAlbums = ArrayList<Album>()
        var test = Album()
        test.artistId = "15457871"
        test.artistName = "John Smith"
        test.collectionName = "First Album"
        newAlbums.add( test )

        var test1 = Album()
        test1.artistId = "15457872"
        test1.artistName = "Hey Smith"
        test1.collectionName = "Sedcond Album"
        newAlbums.add( test1 )

        var test2 = Album()
        test2.artistId = "15457873"
        test2.artistName = "Peter Smith"
        test2.collectionName = "Puuut Album"
        newAlbums.add( test2 )

        var test3 = Album()
        test3.artistId = "15457874"
        test3.artistName = "Steve Smith"
        test3.collectionName = "Yay Album"
        newAlbums.add( test3)

        var test4 = Album()
        test4.artistId = "15457875"
        test4.artistName = "Sandra Smith"
        test4.collectionName = "Fifth Album"
        newAlbums.add( test4)

        var test5 = Album()
        test5.artistId = "15457878"
        test5.artistName = "MArissa Smith"
        test5.collectionName = "ips Album"
        newAlbums.add( test5 )

        var test6 = Album()
        test6.artistId = "15457878"
        test6.artistName = "Suuup Smith"
        test6.collectionName = "kkoooo Album"
        newAlbums.add( test6 )

        var test7 = Album()
        test7.artistId = "15457878"
        test7.artistName = "Aiko Smith"
        test7.collectionName = "Aiko Album"
        newAlbums.add( test7 )

        var test8 = Album()
        test8.artistId = "15457878"
        test8.artistName = "Pamplemousse Smith"
        test8.collectionName = "Pamplemousse Album"
        newAlbums.add( test8 )

        var test9 = Album()
        test9.artistId = "15457878"
        test9.artistName = "Wakalipto Smith"
        test9.collectionName = "Wakalipto Album"
        newAlbums.add( test9 )

        var test10 = Album()
        test10.artistId = "15457878"
        test10.artistName = "Windu Smith"
        test10.collectionName = "Windu Album"
        newAlbums.add( test10 )

        var test11 = Album()
        test11.artistId = "15457878"
        test11.artistName = "Lionel Smith"
        test11.collectionName = "Lionel Album"
        newAlbums.add( test11 )

        var test12 = Album()
        test12.artistId = "15457878"
        test12.artistName = "Groulx Smith"
        test12.collectionName = "Groulx Album"
        newAlbums.add( test12 )

        var test13 = Album()
        test13.artistId = "15457878"
        test13.artistName = "Gato1 Smith"
        test13.collectionName = "GAto1 Album"
        newAlbums.add( test13 )


        albumsList?.setValue(newAlbums)
    }
}
