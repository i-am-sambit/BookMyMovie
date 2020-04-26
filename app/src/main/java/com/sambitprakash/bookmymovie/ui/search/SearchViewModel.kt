package com.sambitprakash.bookmymovie.ui.search

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sambitprakash.bookmymovie.repositoryFacade.networkRepository.NetworkManager
import com.sambitprakash.bookmymovie.repositoryFacade.networkRepository.Result

class SearchViewModel(private val activity: Activity) : ViewModel() {
    private var mMovies = MutableLiveData<ArrayList<SearchMovie>>()
    val movies: LiveData<ArrayList<SearchMovie>> = mMovies

    private var mErrorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = mErrorMessage


    fun searchMovie(name: String) {
        val url = "https://api.themoviedb.org/3/search/movie?api_key=5a439649b46466212e07515d87737c1a&language=en-US&query=${name}&page=1"
        NetworkManager(url).makeRequest<Nothing, SearchResponse> { result ->
            activity.runOnUiThread {
                when (result) {
                    is Result.Success -> mMovies.value = result.response.movies
                    is Result.Failure -> mErrorMessage.value = (result.response.message ?: "")
                }
            }
        }
    }
}
