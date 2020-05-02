package com.sambitprakash.bookmymovie.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.sambitprakash.bookmymovie.BaseActivity
import com.sambitprakash.bookmymovie.R
import com.sambitprakash.bookmymovie.dashboard.MainActivity

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun loginAction(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent)
    }

    fun forgotPasswordAction(view: View) {
        Toast.makeText(
            this,
            "Forgot password is not yet implemented",
            Toast.LENGTH_SHORT).show()
    }
}
