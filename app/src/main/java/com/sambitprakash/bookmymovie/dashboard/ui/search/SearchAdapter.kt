package com.sambitprakash.bookmymovie.dashboard.ui.search

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sambitprakash.bookmymovie.R
import com.sambitprakash.bookmymovie.movieDetails.MovieDetailsActivity
import com.sambitprakash.bookmymovie.utils.date.convertToDate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.search_movie_row.view.*

class SearchAdapter(private val movies: ArrayList<SearchMovie>): RecyclerView.Adapter<SearchViewHolder>() {
    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellView = layoutInflater.inflate(R.layout.search_movie_row, parent, false)
        return SearchViewHolder(cellView)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val movie = movies[position]
        holder.itemView.textView_movieName_search.text = movie.title
        holder.itemView.textView_overview_search.text = movie.releaseDate.convertToDate()

        val posterUrl = "https://image.tmdb.org/t/p/w300_and_h300_bestv2" + movie.posterPath
        Picasso.get().load(posterUrl).into(holder.itemView.imageView_poster_search)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MovieDetailsActivity::class.java)
            intent.putExtra("movie", movie)
            holder.itemView.context.startActivity(intent)
        }
    }
}

class SearchViewHolder(view: View): RecyclerView.ViewHolder(view) {

}