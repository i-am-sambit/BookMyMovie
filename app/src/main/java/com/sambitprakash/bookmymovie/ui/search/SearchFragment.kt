package com.sambitprakash.bookmymovie.ui.search

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sambitprakash.bookmymovie.MainActivity
import com.sambitprakash.bookmymovie.R
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment(), SearchViewModelListener {

    private var viewModel: SearchViewModel = SearchViewModel(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        searchRecyclerView.layoutManager = LinearLayoutManager(this.activity, LinearLayoutManager.VERTICAL, false)

        val divider = DividerItemDecoration(this.activity, DividerItemDecoration.VERTICAL)
        divider.setDrawable(ColorDrawable(requireActivity().getColor(R.color.colorRowDivider)))
        searchRecyclerView.addItemDecoration(divider)

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

    override fun show(movies: ArrayList<SearchMovie>) {
        this.activity?.runOnUiThread {
            (this.activity as MainActivity).loader.dismiss()
            searchRecyclerView.adapter = SearchAdapter(movies)
        }
    }

    override fun showError(message: String) {
        println("Error : $message")
    }

}
