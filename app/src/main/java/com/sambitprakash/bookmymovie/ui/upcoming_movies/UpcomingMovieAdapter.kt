package com.sambitprakash.bookmymovie.ui.upcoming_movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sambitprakash.bookmymovie.R
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
        holderUpcoming.movieName.text = movie.title
        holderUpcoming.overview.text = movie.releaseDate

        val posterUrl = "https://image.tmdb.org/t/p/w300_and_h300_bestv2" + movie.posterPath
        Picasso.get().load(posterUrl).into(holderUpcoming.posterImageView)
    }
}

class UpcomingMovieViewHolder(view: View): RecyclerView.ViewHolder(view) {
    var movieName = view.textView_movieName
    var overview = view.textView_overview
    var posterImageView = view.imageView_poster

    init {
        view.setOnClickListener {
            println("Control inside movie adapter...")
//            val intent = Intent(view.context, MovieDetailsActivity::class.java)
//            view.context.startActivity(intent)
        }
    }
}