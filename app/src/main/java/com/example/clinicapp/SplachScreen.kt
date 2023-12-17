package com.example.clinicapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.clinicapp.authantication.LoginActivity
import com.example.clinicapp.intro.IntroActivity

class SplachScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splach_screen)

          Handler(Looper.myLooper()!!).postDelayed({
            startActivity(Intent(this, IntroActivity::class.java))
          },3000)
    }
}