package com.sambitprakash.bookmymovie.ui.search

import androidx.lifecycle.ViewModel
import com.sambitprakash.bookmymovie.networkManager.NetworkManager
import com.sambitprakash.bookmymovie.networkManager.Result

class SearchViewModel(private val viewModelListener: SearchViewModelListener) : ViewModel() {

    fun searchMovie(name: String) {
        val url = "https://api.themoviedb.org/3/search/movie?api_key=5a439649b46466212e07515d87737c1a&language=en-US&query=${name}&page=1"
        NetworkManager(url).makeRequest<Nothing, SearchResponse> { result ->
            when (result) {
                is Result.Success -> viewModelListener.show(result.response.movies)
                is Result.Failure -> viewModelListener.showError(result.response.message ?: "")
            }
        }
    }
}
