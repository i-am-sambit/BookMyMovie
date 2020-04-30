package com.sambitprakash.bookmymovie.dashboard

import java.io.Serializable

interface MovieInterface: Serializable {
    val id: Int
    val title: String
    val overview: String
    val posterPath: String
    val releaseDate: String
}