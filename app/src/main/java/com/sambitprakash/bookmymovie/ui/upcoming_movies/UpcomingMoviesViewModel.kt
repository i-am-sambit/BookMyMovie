package com.sambitprakash.bookmymovie.ui.upcoming_movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sambitprakash.bookmymovie.networkManager.NetworkManager
import com.sambitprakash.bookmymovie.networkManager.RequestMethod
import com.sambitprakash.bookmymovie.networkManager.Result

data class PostData(val username: String, val password: String)

class UpcomingMoviesViewModel(private val viewModelListener: UpcomingMovieViewModelListener) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    fun fetchUpcomingMovies() {
        val url = "https://api.themoviedb.org/3/movie/popular?api_key=5a439649b46466212e07515d87737c1a&language=en-US&page=1"
        NetworkManager(url, RequestMethod.POST).makeRequest<Nothing, MoviesResponse> { result ->
            when (result) {
                is Result.Success -> viewModelListener.show(result.response.movies)
                is Result.Failure -> viewModelListener.showError(result.response.message ?: "")
            }
        }
    }
}