package com.sambitprakash.bookmymovie.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sambitprakash.bookmymovie.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.home_trending_movie_row.view.*

class HomeTrendingAdapter(private val movies: ArrayList<HomeMovie>): RecyclerView.Adapter<HomeMovieViewHolder>() {

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellView = layoutInflater.inflate(R.layout.home_trending_movie_row, parent, false)
        return HomeMovieViewHolder(cellView)
    }

    override fun onBindViewHolder(holderHome: HomeMovieViewHolder, position: Int) {
        val movie = movies[position]
        val posterUrl = "https://image.tmdb.org/t/p/w300_and_h300_bestv2" + movie.posterPath
        Picasso.get().load(posterUrl).into(holderHome.itemView.imageView_poster_trending_home)
    }
}

class HomeTrendingMovieViewHolder(view: View): RecyclerView.ViewHolder(view)