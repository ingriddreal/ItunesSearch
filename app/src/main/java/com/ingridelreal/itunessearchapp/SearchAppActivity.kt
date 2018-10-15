package com.ingridelreal.itunessearchapp

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import com.ingridelreal.itunessearchapp.viewModel.ArtistViewModel
import android.support.v7.widget.RecyclerView
import android.view.*
import com.ingridelreal.itunessearchapp.model.Album
import com.ingridelreal.itunessearchapp.data.remote.APIService
import com.ingridelreal.itunessearchapp.data.remote.ApiUtils
import android.widget.SearchView

class SearchAppActivity : AppCompatActivity(), AlbumAdapter.OnItemClickListener, SearchView.OnQueryTextListener{

    private var mAlbumAdapter: AlbumAdapter? = null
    private var mArtistViewModel: ArtistViewModel? = null
    private var mAPIService: APIService? = null
    private var searchQuery: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_app)

        val albumsRecyclerView = findViewById<View>(R.id.rvTodos) as RecyclerView

        albumsRecyclerView.layoutManager = LinearLayoutManager(this)
        mAlbumAdapter = AlbumAdapter(arrayListOf(), this, context = this)

        mAPIService = ApiUtils.apiService

        albumsRecyclerView.adapter = mAlbumAdapter
        mArtistViewModel = ViewModelProviders.of(this).get(ArtistViewModel::class.java)


        val searchView = findViewById(R.id.searchbox) as SearchView // inititate a search view

        searchView.setOnQueryTextListener(this)
    }

    override fun onQueryTextChange(query: String?): Boolean {
        callSearch(query)
        return true
    }

    override fun onQueryTextSubmit(newText: String?): Boolean {
        callSearch(newText)
        return true
    }

    fun callSearch(query: String?) {
        searchQuery = query
        mArtistViewModel?.getAlbunsList(searchQuery, "album")?.observe(this, Observer {
            albums -> albums.let {
            if (it != null)
                mAlbumAdapter?.updateAlbums(it) }
        })
    }

    override fun onItemClick(album: Album) {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Album information")
        alert.setMessage(album.primaryGenreName?.capitalize()+"\n"+
        album.collectionPrice+" "+ album.currency+"\n"+
        album.copyright)
        alert.setPositiveButton("OK", null)
        alert.show()
    }
}
