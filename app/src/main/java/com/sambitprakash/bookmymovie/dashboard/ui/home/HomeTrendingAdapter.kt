package com.sambitprakash.bookmymovie.dashboard.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sambitprakash.bookmymovie.R
import com.sambitprakash.bookmymovie.movieDetails.MovieDetailsActivity
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

//        holderHome.itemView.indexTextView.text = "${position+1}" + "/" + "${movies.size}"

        val posterUrl = "https://image.tmdb.org/t/p/w300_and_h300_bestv2" + movie.posterPath
        Picasso.get().load(posterUrl).into(holderHome.itemView.imageView_poster_trending_home)

        holderHome.itemView.setOnClickListener {
            val intent = Intent(holderHome.itemView.context, MovieDetailsActivity::class.java)
            intent.putExtra("movie", movie)
            holderHome.itemView.context.startActivity(intent)
        }
    }
}

class HomeTrendingMovieViewHolder(view: View): RecyclerView.ViewHolder(view)