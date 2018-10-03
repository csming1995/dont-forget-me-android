package com.csming.dontforgetme

import android.content.Intent
import android.os.Bundle
import com.csming.dontforgetme.login.activity.LoginActivity
import dagger.android.support.DaggerAppCompatActivity

class LaucherActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laucher)

        startNextActivity()
    }

    private fun startNextActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
