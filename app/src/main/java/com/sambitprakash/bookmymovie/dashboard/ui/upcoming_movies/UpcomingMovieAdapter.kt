package com.sambitprakash.bookmymovie.dashboard.ui.upcoming_movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sambitprakash.bookmymovie.R
import com.sambitprakash.bookmymovie.movieDetails.MovieDetailsActivity
import com.sambitprakash.bookmymovie.utils.date.convertToDate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.upcoming_movie_row.view.*

class UpcomingMovieAdapter(private val movies: ArrayList<Movie>): RecyclerView.Adapter<UpcomingMovieViewHolder>() {

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingMovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellView = layoutInflater.inflate(R.layout.upcoming_movie_row, parent, false)
        return UpcomingMovieViewHolder(cellView)
    }

    override fun onBindViewHolder(holderUpcoming: UpcomingMovieViewHolder, position: Int) {
        val movie = movies[position]
        holderUpcoming.itemView.textView_movieName.text = movie.title
        holderUpcoming.itemView.textView_overview.text = movie.releaseDate.convertToDate()

        val posterUrl = "https://image.tmdb.org/t/p/w300_and_h300_bestv2" + movie.posterPath
        Picasso.get().load(posterUrl).into(holderUpcoming.itemView.imageView_poster)

        holderUpcoming.itemView.setOnClickListener {
            val intent = Intent(holderUpcoming.itemView.context, MovieDetailsActivity::class.java)
            intent.putExtra("movie", movie)
            holderUpcoming.itemView.context.startActivity(intent)
        }
    }
}

class UpcomingMovieViewHolder(view: View): RecyclerView.ViewHolder(view)