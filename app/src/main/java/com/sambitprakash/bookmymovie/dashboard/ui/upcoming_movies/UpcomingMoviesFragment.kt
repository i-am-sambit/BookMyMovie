package com.sambitprakash.bookmymovie.dashboard.ui.upcoming_movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sambitprakash.bookmymovie.BaseFragment
import com.sambitprakash.bookmymovie.dashboard.MainActivity
import com.sambitprakash.bookmymovie.R
import kotlinx.android.synthetic.main.fragment_upcoming_movie.*

class UpcomingMoviesFragment : Fragment(), BaseFragment {
    private lateinit var mViewModel: UpcomingMoviesViewModel

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_upcoming_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialSetup()
        fetchUpcomingMovies()
        setDataSourceObservers()
    }

    override fun initialSetup() {
        mViewModel = UpcomingMoviesViewModel(this.requireActivity())
        moviesRecyclerView.layoutManager = LinearLayoutManager(this.activity)
    }

    private fun fetchUpcomingMovies() {
        mViewModel.fetchUpcomingMovies()
        (this.activity as MainActivity).loader.start()
    }

    override fun setDataSourceObservers() {
        val moviesObserver = Observer {movies: ArrayList<Movie> ->
            (this.activity as MainActivity).loader.dismiss()
            moviesRecyclerView.adapter = UpcomingMovieAdapter(movies)
        }
        mViewModel.movies.observe(viewLifecycleOwner, moviesObserver)

        val errorObserver = Observer {message: String ->
            (this.activity as MainActivity).loader.dismiss()
            Toast.makeText(this.activity, message, Toast.LENGTH_LONG).show()
        }
        mViewModel.errorMessage.observe(viewLifecycleOwner, errorObserver)
    }

}