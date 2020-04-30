package com.sambitprakash.bookmymovie.dashboard.ui.home

import com.google.gson.annotations.SerializedName
import com.sambitprakash.bookmymovie.dashboard.MovieInterface

data class MovieCategory(
    val id: Int,
    val name: String,
    val movies: ArrayList<HomeMovie>)

data class HomeResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("results") val movies: ArrayList<HomeMovie>)

data class HomeMovie(
    @SerializedName("id") override val id: Int,
    @SerializedName("title") override val title: String,
    @SerializedName("overview") override val overview: String,
    @SerializedName("poster_path") override val posterPath: String,
    @SerializedName("release_date") override val releaseDate: String): MovieInterface
