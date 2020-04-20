package com.sambitprakash.bookmymovie.ui.home

import com.google.gson.annotations.SerializedName


class MovieCategory(
    val id: Int,
    val name: String,
    val movies: ArrayList<HomeMovie>)

data class HomeResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("results") val movies: ArrayList<HomeMovie>)

data class HomeMovie(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String)
