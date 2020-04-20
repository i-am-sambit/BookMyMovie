package com.sambitprakash.bookmymovie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sambitprakash.bookmymovie.loader.Loader

open class BaseActivity: AppCompatActivity() {
    lateinit var loader: Loader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createLoader()
    }

    private fun createLoader() {
        loader = Loader(this)
    }
}