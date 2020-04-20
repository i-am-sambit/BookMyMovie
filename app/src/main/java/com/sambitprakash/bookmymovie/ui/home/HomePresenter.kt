package com.sambitprakash.bookmymovie.ui.home

interface HomePresenter {
    fun show(homeMovies: ArrayList<MovieCategory>)
    fun showError(message: String)
}