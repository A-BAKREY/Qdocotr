package com.example.clinicapp.intro

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2
import com.example.clinicapp.R
import com.example.clinicapp.authantication.LoginActivity
import com.example.clinicapp.intro.adapter.IntroApater

class IntroActivity : AppCompatActivity() {
    private lateinit var pagerAdapter: IntroApater
    private val PREFS_NAME = "MyPrefs"
    private val PREF_FIRST_RUN = "firstRun"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val nextBtn = findViewById<Button>(R.id.btnNext)

        pagerAdapter = IntroApater(this)
        viewPager.adapter = pagerAdapter

        val btnSkip: Button = findViewById(R.id.btnSkip)

        btnSkip.setOnClickListener {
            // Navigate to the main activity
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        nextBtn.setOnClickListener {
            if (viewPager.currentItem < pagerAdapter.itemCount - 1) {
                viewPager.currentItem += 1
            } else {
                // User has completed onboarding, navigate to the main activity
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }
}