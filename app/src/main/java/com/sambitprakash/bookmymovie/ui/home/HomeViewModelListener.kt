package com.sambitprakash.bookmymovie.ui.home

interface HomeViewModelListener {
    fun show(homeMovies: ArrayList<MovieCategory>)
    fun showError(message: String)
}