package com.sambitprakash.bookmymovie.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sambitprakash.bookmymovie.MainActivity
import com.sambitprakash.bookmymovie.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), HomePresenter {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View,
                               savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(
            this.context, LinearLayoutManager.VERTICAL, false)
        moviesRecyclerView.layoutManager = layoutManager

        homeViewModel = HomeViewModel(this)
        (this.activity as MainActivity).loader.start()
    }

    override fun show(homeMovies: ArrayList<MovieCategory>) {
        this.activity?.runOnUiThread {
            (this.activity as MainActivity).loader.dismiss()
            moviesRecyclerView.adapter = HomeCategoryAdapter(this.context, homeMovies)
        }
    }

    override fun showError(message: String) {

    }
}