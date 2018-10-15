package com.ingridelreal.itunessearchapp

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.ingridelreal.itunessearchapp.viewModel.ArtistViewModel
import android.support.v7.widget.RecyclerView
import android.view.*
import com.ingridelreal.itunessearchapp.model.Album
import com.ingridelreal.itunessearchapp.data.remote.APIService
import com.ingridelreal.itunessearchapp.data.remote.ApiUtils

class SearchAppActivity : AppCompatActivity(), AlbumAdapter.OnItemClickListener {

    private var mAlbumAdapter: AlbumAdapter? = null
    private var mArtistViewModel: ArtistViewModel? = null
    private var mAPIService: APIService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_app)

        val albumsRecyclerView = findViewById<View>(R.id.rvTodos) as RecyclerView

        albumsRecyclerView.layoutManager = LinearLayoutManager(this)
        mAlbumAdapter = AlbumAdapter(arrayListOf(), this, context = this)

        mAPIService = ApiUtils.apiService

        albumsRecyclerView.adapter = mAlbumAdapter
        mArtistViewModel = ViewModelProviders.of(this).get(ArtistViewModel::class.java)

        mArtistViewModel?.getAlbunsList()?.observe(this, Observer {
            albums -> albums.let {
            if (it != null)
                mAlbumAdapter?.updateAlbums(it) }
        })
    }

    override fun onItemClick(album: Album) {
      //TODO: START POPUP HERE
    }
}
