package com.example.clinicapp.authantication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.clinicapp.MainActivity
import com.example.clinicapp.department.Department
import com.example.clinicapp.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var goToSignUp =findViewById<TextView>(R.id.gotoSign)
        goToSignUp.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
        var gotohome =findViewById<Button>(R.id.login)
        gotohome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
    }
}
}