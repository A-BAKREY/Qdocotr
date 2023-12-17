package com.example.clinicapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.clinicapp.databinding.ActivityMainBinding
import com.example.clinicapp.department.Department
import com.example.clinicapp.docotor.Doctor
import com.example.clinicapp.docotor.DoctorInformation


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val homeFragment = Department()
    private val dashboardFragment = Doctor()
    private val profileFragment = DoctorInformation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val bottomNavigationView = binding.bottomNav
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> replaceFragment(homeFragment)
                R.id.navigation_dashboard -> replaceFragment(dashboardFragment)
                R.id.navigation_profile -> replaceFragment(profileFragment)
            }
            true
        }

        // Set the default fragment
        replaceFragment(homeFragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }
}