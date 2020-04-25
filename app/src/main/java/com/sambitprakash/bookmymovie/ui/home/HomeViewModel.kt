package com.sambitprakash.bookmymovie.ui.home

import androidx.lifecycle.ViewModel
import com.sambitprakash.bookmymovie.networkManager.NetworkManager
import com.sambitprakash.bookmymovie.networkManager.Result
import kotlin.properties.Delegates

class HomeViewModel(private val viewModelListener: HomeViewModelListener) : ViewModel() {
    private var categories: ArrayList<MovieCategory> by Delegates.observable(ArrayList()) {
        _, _, newValue ->
        if (newValue.size == 4) {
            newValue.sortBy { it.id }
            viewModelListener.show(newValue)
        }
    }

    init {
        fetchTrendingMovies()
        fetchPopularMovies()
        fetchNowPlayingMovies()
        fetchTopRatedMovies()
    }

    private fun fetchTrendingMovies() {
        val url = "https://api.themoviedb.org/3/trending/movie/week?api_key=5a439649b46466212e07515d87737c1a"
        NetworkManager(url).makeRequest<Nothing, HomeResponse> { result ->
            when (result) {
                is Result.Success -> {
                    categories.add(MovieCategory(0,"Popular", result.response.movies))
                    categories = categories
                }

                is Result.Failure -> {
                    viewModelListener.showError(result.response.message ?: "")
                }
            }
        }
    }

    private fun fetchPopularMovies() {
        val url = "https://api.themoviedb.org/3/movie/popular?api_key=5a439649b46466212e07515d87737c1a&language=en-US&page=1"
        NetworkManager(url).makeRequest<Nothing, HomeResponse> { result ->
            when (result) {
                is Result.Success -> {
                    categories.add(MovieCategory(2,"Popular", result.response.movies))
                    categories = categories
                }

                is Result.Failure -> {
                    viewModelListener.showError(result.response.message ?: "")
                }
            }
        }
    }

    private fun fetchNowPlayingMovies() {
        val url = "https://api.themoviedb.org/3/movie/now_playing?api_key=5a439649b46466212e07515d87737c1a&language=en-US&page=1"
        NetworkManager(url).makeRequest<Nothing, HomeResponse> { result ->
            when (result) {
                is Result.Success -> {
                    categories.add(MovieCategory(1,"Now Playing", result.response.movies))
                    categories = categories
                }

                is Result.Failure -> {
                    viewModelListener.showError(result.response.message ?: "")
                }
            }
        }
    }

    private fun fetchTopRatedMovies() {
        val url = "https://api.themoviedb.org/3/movie/top_rated?api_key=5a439649b46466212e07515d87737c1a&language=en-US&page=1"
        NetworkManager(url).makeRequest<Nothing, HomeResponse> { result ->
            when (result) {
                is Result.Success -> {
                    categories.add(MovieCategory(3,"Top Rated", result.response.movies))
                    categories = categories
                }

                is Result.Failure -> {
                    viewModelListener.showError(result.response.message ?: "")
                }
            }
        }
    }

}