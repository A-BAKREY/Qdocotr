package com.example.clinicapp.authantication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.clinicapp.Bookinglist.BookingList
import com.example.clinicapp.MainActivity
import com.example.clinicapp.department.Department
import com.example.clinicapp.R
import com.example.clinicapp.admin

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var mail = findViewById<EditText>(R.id.mail)
        var password = findViewById<EditText>(R.id.password)


        var goToSignUp = findViewById<TextView>(R.id.gotoSign)
        goToSignUp.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
        var gotohome = findViewById<Button>(R.id.login)
        gotohome.setOnClickListener {
            if (mail.text.equals("1")) {
                startActivity(Intent(this, BookingList::class.java))
            } else if (mail.text.equals("admin")) {
                startActivity(Intent(this, admin::class.java))
            } else {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }

    }
}

