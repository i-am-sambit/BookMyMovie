package com.sambitprakash.bookmymovie.dashboard.ui.search

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sambitprakash.bookmymovie.dashboard.MainActivity
import com.sambitprakash.bookmymovie.R
import kotlinx.android.synthetic.main.fragment_search.*
import kotlin.collections.ArrayList
import androidx.lifecycle.Observer
import com.sambitprakash.bookmymovie.BaseFragment

class SearchFragment : BaseFragment() {
    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setSearchViewObservers()
    }

     override fun initialSetup() {
        viewModel = SearchViewModel(this.requireActivity())
        searchRecyclerView.layoutManager = LinearLayoutManager(this.activity, LinearLayoutManager.VERTICAL, false)

        val divider = DividerItemDecoration(this.activity, DividerItemDecoration.VERTICAL)
        divider.setDrawable(ColorDrawable(requireActivity().getColor(R.color.colorRowDivider)))
        searchRecyclerView.addItemDecoration(divider)
    }

    private fun setSearchViewObservers() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (p0 != null) {
                    viewModel.searchMovie(p0)
                    (activity as MainActivity).loader.start()
                }
                return false
            }
        })
    }

     override fun setDataSourceObservers() {
        val moviesObserver = Observer { movies: ArrayList<SearchMovie> ->
            (this.activity as MainActivity).loader.dismiss()
            searchRecyclerView.adapter = SearchAdapter(movies)
        }
        viewModel.movies.observe(viewLifecycleOwner, moviesObserver)

        val errorObserver = Observer { message: String ->
            (this.activity as MainActivity).loader.dismiss()
            this.showToast(message)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner, errorObserver)
    }

}
