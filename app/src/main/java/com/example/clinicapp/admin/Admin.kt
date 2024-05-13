package com.example.clinicapp.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.clinicapp.R
import com.example.clinicapp.databinding.AdminBinding
import com.example.clinicapp.adddoctor.AddDoctor
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
    }
}