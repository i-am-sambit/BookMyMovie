package com.sambitprakash.bookmymovie.loader

import android.app.Activity
import android.app.AlertDialog
import com.sambitprakash.bookmymovie.R

class Loader(private val activity: Activity) {
    lateinit var dialog: AlertDialog

    fun start() {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.custom_dialog, null))
        builder.setCancelable(false)

        dialog = builder.create()
        dialog.show()
    }

    fun dismiss() {
        dialog.dismiss()
    }
}