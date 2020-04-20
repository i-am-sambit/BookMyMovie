package com.sambitprakash.bookmymovie.ui.upcoming_movies

class MoviesResponse(val page: Int,
                     val total_results: Int,
                     val total_pages: Int,
                     val results: ArrayList<Movie>)

class Movie(val id: Int,
            val title: String,
            val overview: String,
            val poster_path: String,
            val release_date: String)
