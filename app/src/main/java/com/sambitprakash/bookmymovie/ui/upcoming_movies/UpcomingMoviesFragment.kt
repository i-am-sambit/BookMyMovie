package com.sambitprakash.bookmymovie.ui.upcoming_movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sambitprakash.bookmymovie.MainActivity
import com.sambitprakash.bookmymovie.R
import kotlinx.android.synthetic.main.fragment_upcoming_movie.*

class UpcomingMoviesFragment : Fragment(), UpcomingMovieViewModelListener {

    private var dashboardViewModel = UpcomingMoviesViewModel(this)

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_upcoming_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesRecyclerView.layoutManager = LinearLayoutManager(this.activity)
        dashboardViewModel.fetchUpcomingMovies()
        (this.activity as MainActivity).loader.start()
    }

    override fun show(movies: ArrayList<Movie>) {
        this.activity?.runOnUiThread {
            (this.activity as MainActivity).loader.dismiss()
            moviesRecyclerView.adapter = UpcomingMovieAdapter(movies)
        }
    }

    override fun showError(message: String) {

    }
}