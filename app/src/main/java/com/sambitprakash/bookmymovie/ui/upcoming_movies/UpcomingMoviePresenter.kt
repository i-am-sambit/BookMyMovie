package com.sambitprakash.bookmymovie.ui.upcoming_movies

interface UpcomingMoviePresenter {
    fun show(movies: ArrayList<Movie>)
    fun showError(message: String)
}