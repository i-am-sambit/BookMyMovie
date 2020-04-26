package com.sambitprakash.bookmymovie.ui.upcoming_movies

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sambitprakash.bookmymovie.networkManager.NetworkManager
import com.sambitprakash.bookmymovie.networkManager.RequestMethod
import com.sambitprakash.bookmymovie.networkManager.Result

class UpcomingMoviesViewModel(private val viewModelListener: UpcomingMovieViewModelListener, private val activity: Activity) : ViewModel() {
    private var mMovies = MutableLiveData<ArrayList<Movie>>()
    val movies: LiveData<ArrayList<Movie>> = mMovies

    fun fetchUpcomingMovies() {
        val url = "https://api.themoviedb.org/3/movie/upcoming?api_key=5a439649b46466212e07515d87737c1a&language=en-US&page=1"
        NetworkManager(url, RequestMethod.POST).makeRequest<Nothing, MoviesResponse> { result ->
            activity.runOnUiThread {
                when (result) {
                    is Result.Success -> mMovies.value = result.response.movies
                    is Result.Failure -> viewModelListener.showError(result.response.message ?: "")
                }
            }
        }
    }
}