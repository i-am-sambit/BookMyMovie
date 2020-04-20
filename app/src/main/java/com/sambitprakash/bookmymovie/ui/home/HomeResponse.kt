package com.sambitprakash.bookmymovie.ui.home


class MovieCategory(
    val id: Int,
    val name: String,
    val movies: ArrayList<HomeMovie>)

class HomeResponse(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: ArrayList<HomeMovie>)

class HomeMovie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String)
