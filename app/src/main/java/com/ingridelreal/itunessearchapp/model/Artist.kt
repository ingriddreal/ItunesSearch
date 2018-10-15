package com.ingridelreal.itunessearchapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Artist {
    @SerializedName("resultCount")
    @Expose
    var resultCount: Int? = null
    @SerializedName("results")
    @Expose
    var albums: List<Album>? = null

}