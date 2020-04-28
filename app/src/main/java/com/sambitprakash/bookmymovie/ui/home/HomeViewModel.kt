package com.sambitprakash.bookmymovie.ui.home

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sambitprakash.bookmymovie.repositoryFacade.networkRepository.NetworkManager
import com.sambitprakash.bookmymovie.repositoryFacade.networkRepository.Result

class HomeViewModel(private val activity: Activity) : ViewModel() {
    private var mCategories = MutableLiveData<ArrayList<MovieCategory>>()
    val categories: LiveData<ArrayList<MovieCategory>> = mCategories

    private var mErrorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = mErrorMessage

    init {
        fetchTrendingMovies()
        fetchPopularMovies()
        fetchNowPlayingMovies()
        fetchTopRatedMovies()
    }

    private fun fetchTrendingMovies() {
        val url = "https://api.themoviedb.org/3/trending/movie/week?api_key=5a439649b46466212e07515d87737c1a"
        NetworkManager(url).makeRequest<Nothing, HomeResponse> { result ->
            activity.runOnUiThread {
                when (result) {
                    is Result.Success -> {
                        add(MovieCategory(0,"", result.response.movies))
                    }

                    is Result.Failure -> {
                        mErrorMessage.value = result.response.message ?: ""
                    }
                }
            }
        }
    }

    private fun fetchPopularMovies() {
        val url = "https://api.themoviedb.org/3/movie/popular?api_key=5a439649b46466212e07515d87737c1a&language=en-US&page=1"
        NetworkManager(url).makeRequest<Nothing, HomeResponse> { result ->
            activity.runOnUiThread {
                when (result) {
                    is Result.Success -> {
                        add(MovieCategory(2, "Popular", result.response.movies))
                    }

                    is Result.Failure -> {
                        mErrorMessage.value = result.response.message ?: ""
                    }
                }
            }
        }
    }

    private fun fetchNowPlayingMovies() {
        val url = "https://api.themoviedb.org/3/movie/now_playing?api_key=5a439649b46466212e07515d87737c1a&language=en-US&page=1"
        NetworkManager(url).makeRequest<Nothing, HomeResponse> { result ->
            activity.runOnUiThread {
                when (result) {
                    is Result.Success -> {
                        add(MovieCategory(1,"Now Playing", result.response.movies))
                    }

                    is Result.Failure -> {
                        mErrorMessage.value = result.response.message ?: ""
                    }
                }
            }
        }
    }

    private fun fetchTopRatedMovies() {
        val url = "https://api.themoviedb.org/3/movie/top_rated?api_key=5a439649b46466212e07515d87737c1a&language=en-US&page=1"
        NetworkManager(url).makeRequest<Nothing, HomeResponse> { result ->
            activity.runOnUiThread {
                when (result) {
                    is Result.Success -> {
                        add(MovieCategory(3,"Top Rated", result.response.movies))
                    }

                    is Result.Failure -> {
                        mErrorMessage.value = result.response.message ?: ""
                    }
                }
            }
        }
    }

    private fun add(category: MovieCategory) {
        val movieCategories = mCategories.value ?: ArrayList()
        movieCategories.add(category)
        movieCategories.sortBy { it.id }
        mCategories.value = movieCategories
    }

}