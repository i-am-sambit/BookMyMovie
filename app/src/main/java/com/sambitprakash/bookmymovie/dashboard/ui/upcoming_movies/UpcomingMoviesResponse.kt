package com.sambitprakash.bookmymovie.dashboard.ui.upcoming_movies

import com.google.gson.annotations.SerializedName
import com.sambitprakash.bookmymovie.dashboard.MovieInterface

data class MoviesResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("results") val movies: ArrayList<Movie>)

data class Movie(
    @SerializedName("id") override val id: Int,
    @SerializedName("title") override val title: String,
    @SerializedName("overview") override val overview: String,
    @SerializedName("poster_path") override val posterPath: String,
    @SerializedName("release_date") override val releaseDate: String): MovieInterface
