package com.sambitprakash.bookmymovie.ui.upcoming_movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sambitprakash.bookmymovie.BaseFragment
import com.sambitprakash.bookmymovie.MainActivity
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
        val moviesObserver = Observer<ArrayList<Movie>> {
            (this.activity as MainActivity).loader.dismiss()
            moviesRecyclerView.adapter = UpcomingMovieAdapter(mViewModel.movies.value ?: ArrayList())
        }
        mViewModel.movies.observe(viewLifecycleOwner, moviesObserver)

        val errorObserver = Observer<String> {
            (this.activity as MainActivity).loader.dismiss()
            //TODO : Show Error message in UI
        }
        mViewModel.errorMessage.observe(viewLifecycleOwner, errorObserver)
    }

}