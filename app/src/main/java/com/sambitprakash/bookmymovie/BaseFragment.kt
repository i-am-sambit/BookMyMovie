package com.sambitprakash.bookmymovie

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

open class BaseFragment: Fragment() {

    override fun onViewCreated(view: View,
                               savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialSetup()
        setDataSourceObservers()
    }

    open fun initialSetup() {

    }

    open fun setDataSourceObservers() {

    }

    fun showToast(message: String) {
        Toast.makeText(this.activity, message, Toast.LENGTH_LONG).show()
    }
}