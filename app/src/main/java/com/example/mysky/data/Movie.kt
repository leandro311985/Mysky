package com.example.mysky.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Movie : Serializable {
    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("overview")
    @Expose
    var overview: String? = null

    @SerializedName("duration")
    @Expose
    var duration: String? = null

    @SerializedName("release_year")
    @Expose
    var releaseYear: String? = null

    @SerializedName("cover_url")
    @Expose
    var coverUrl: String? = null

    @SerializedName("backdrops_url")
    @Expose
    var backdropsUrl: List<String>? = null


}