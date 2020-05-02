package com.sambitprakash.bookmymovie.dashboard.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sambitprakash.bookmymovie.BaseFragment
import com.sambitprakash.bookmymovie.dashboard.MainActivity
import com.sambitprakash.bookmymovie.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    private lateinit var mHomeViewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun initialSetup() {
        val layoutManager = LinearLayoutManager(
            this.context, LinearLayoutManager.VERTICAL, false)
        moviesRecyclerView.layoutManager = layoutManager

        mHomeViewModel = HomeViewModel(this.requireActivity())
        (this.activity as MainActivity).loader.start()
    }

    override fun setDataSourceObservers() {
        val categoryObserver = Observer { categories: ArrayList<MovieCategory> ->
            (this.activity as MainActivity).loader.dismiss()
            moviesRecyclerView.adapter = HomeCategoryAdapter(this.context, categories)
        }
        mHomeViewModel.categories.observe(viewLifecycleOwner, categoryObserver)

        val errorObserver = Observer { message: String ->
            (this.activity as MainActivity).loader.dismiss()
            this.showToast(message)
        }
        mHomeViewModel.errorMessage.observe(viewLifecycleOwner, errorObserver)
    }
}