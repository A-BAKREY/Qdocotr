package com.example.clinicapp.authantication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.clinicapp.R

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        var l = findViewById<TextView>(R.id.gotologin)
        l.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}