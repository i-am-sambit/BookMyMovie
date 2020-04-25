package com.sambitprakash.bookmymovie.ui.search

interface SearchViewModelListener {
    fun show(movies: ArrayList<SearchMovie>)
    fun showError(message: String)
}