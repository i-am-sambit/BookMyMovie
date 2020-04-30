package com.sambitprakash.bookmymovie.dashboard.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.sambitprakash.bookmymovie.R

class ProfileFragment : Fragment() {

    private var profileViewModel: ProfileViewModel = ProfileViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        profileViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}