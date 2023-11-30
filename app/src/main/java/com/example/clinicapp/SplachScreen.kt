package com.example.clinicapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SplachScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splach_screen)
        var text = findViewById<TextView>(R.id.gotoMain)
        text.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))

        }

            }
}