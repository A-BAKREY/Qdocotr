package com.example.clinicapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.clinicapp.Bookinglist.BookingList
import com.example.clinicapp.admin.Admin
import com.example.clinicapp.intro.IntroActivity

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splach_screen)

        Handler(Looper.myLooper()!!).postDelayed({
            val isLoggedIn = checkLoginStatus()

            if (isLoggedIn) {
                openLoggedInActivity()
            } else {
                startActivity(Intent(this, IntroActivity::class.java))
            }
            finish()
        }, 3000)
    }

    private fun checkLoginStatus(): Boolean {
        val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("userType", "")
        return !username.isNullOrEmpty()
    }
    private fun openLoggedInActivity() {
        val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val userType = sharedPreferences.getString("userType", "")

        val intent = when (userType) {
            "A" -> Intent(this, Admin::class.java)
            "D" -> Intent(this, BookingList::class.java)
            "P" -> Intent(this, MainActivity::class.java)
            else -> Intent(this, MainActivity::class.java) // Default activity if userType is unknown
        }

        startActivity(intent)
    }

}