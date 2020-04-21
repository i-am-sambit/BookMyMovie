package com.sambitprakash.bookmymovie.ui.upcoming_movies

interface UpcomingMovieViewModelListener {
    fun show(movies: ArrayList<Movie>)
    fun showError(message: String)
}