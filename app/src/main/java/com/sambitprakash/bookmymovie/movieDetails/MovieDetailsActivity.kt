package com.sambitprakash.bookmymovie.movieDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sambitprakash.bookmymovie.R
import com.sambitprakash.bookmymovie.dashboard.MovieInterface
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var movie: MovieInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        movie = intent.getSerializableExtra("movie") as MovieInterface
        this.title = movie.title

        val posterUrl = "https://image.tmdb.org/t/p/w300_and_h300_bestv2" + movie.posterPath
        Picasso.get().load(posterUrl).into(posterImageView)
    }
}
