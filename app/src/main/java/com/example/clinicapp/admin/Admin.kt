package com.example.clinicapp.admin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.clinicapp.R
import com.example.clinicapp.databinding.AdminBinding
import com.example.clinicapp.adddoctor.AddDoctor
import com.example.clinicapp.authantication.LoginActivity
import com.example.clinicapp.profile.ProfileFragment

class Admin : AppCompatActivity() {
    private lateinit var binding: AdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.addDoctor.setOnClickListener {
            startActivity(Intent(this, AddDoctor::class.java))
        }
        binding.signOut.setOnClickListener {
            logout()
        }
    }
    private fun logout() {
        val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        AlertDialog.Builder(this)
            .setTitle("Confirm Logout")
            .setMessage("Are you sure you want to sign out?")
            .setPositiveButton("yes") { dialog, _ ->
                editor.clear()
                editor.apply()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                dialog.dismiss()
            }
            .setNegativeButton("no") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}