package com.ingridelreal.itunessearchapp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.ingridelreal.itunessearchapp.model.Album
import com.bumptech.glide.request.RequestOptions
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter


class AlbumAdapter (private val albums : List<Album>, listener: OnItemClickListener, context : Context): RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    private var listAlbums: List<Album> = albums
    private var listenerContact: OnItemClickListener = listener
    private val context: Context = context

    interface OnItemClickListener {
        fun onItemClick(album: Album)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return AlbumViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        var currentAlbum : Album = listAlbums[position]

        holder.albumName.setText(currentAlbum?.collectionName)

        val desiredFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val releaseDateString = currentAlbum?.releaseDate
        val dateTime = OffsetDateTime.parse(releaseDateString)
        val presentationDateTimeString = dateTime.format(desiredFormatter)

        holder.releaseDate.setText( presentationDateTimeString)

        val options = RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round)

        Glide.with(this.context).load(currentAlbum?.artworkUrl100).apply(options).into(holder.albumWorkart)
        holder.bind(currentAlbum, listenerContact)
    }

    fun updateAlbums(listAlbums: List<Album>) {
        this.listAlbums = listAlbums
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return listAlbums.size
    }

    inner class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val albumName: TextView
        val releaseDate: TextView
        val albumWorkart : ImageView

        init {
            albumName = itemView.findViewById(R.id.albumName)
            releaseDate = itemView.findViewById(R.id.releaseDate)

            albumWorkart = itemView.findViewById(R.id.albumWorkart)

        }

        fun bind(album: Album, listener: OnItemClickListener) {
            itemView.setOnClickListener {
                listener.onItemClick(album)
            }
        }
    }
}
