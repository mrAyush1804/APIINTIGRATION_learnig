package com.example.api

import com.google.gson.annotations.SerializedName

data class Movie(
    val title: String,
    val release_date: String,
    val vote_average: Float,
    @SerializedName("poster_path") val image: String?

) {

}

data class MovieResponse(
    val results: List<Movie>
)