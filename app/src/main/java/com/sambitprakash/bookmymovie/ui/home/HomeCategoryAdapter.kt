package com.sambitprakash.bookmymovie.ui.home

import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sambitprakash.bookmymovie.R
import kotlinx.android.synthetic.main.home_movie_section.view.*

class HomeCategoryAdapter(private val context: Context?,
                          private val categories: ArrayList<MovieCategory>): RecyclerView.Adapter<HomeCategoryViewHolder>() {

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val categoryCellView = layoutInflater.inflate(
            R.layout.home_movie_section, parent, false)
        return HomeCategoryViewHolder(categoryCellView)
    }

    override fun onBindViewHolder(holder: HomeCategoryViewHolder, position: Int) {
        val layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.HORIZONTAL, false)
        holder.itemView.recyclerView_category.layoutManager = layoutManager
        holder.itemView.recyclerView_category.addItemDecoration(HomeGridItemDecorator(30))

        if (position == 0) {
            holder.itemView.textView_section.visibility = View.GONE
            holder.itemView.recyclerView_category.adapter = HomeTrendingAdapter(categories[0].movies)
        } else {
            holder.itemView.recyclerView_category.adapter = HomeAdapter(categories[position].movies)
            holder.itemView.textView_section.text = categories[position].name
        }

    }
}

class HomeCategoryViewHolder(view: View): RecyclerView.ViewHolder(view)