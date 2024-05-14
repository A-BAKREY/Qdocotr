package com.example.clinicapp.Bookinglist

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clinicapp.Bookinglist.model.BookingModel
import com.example.clinicapp.Bookinglist.viewmodel.adapter.BookingListAdapter
import com.example.clinicapp.R
import com.example.clinicapp.authantication.LoginActivity
import com.example.clinicapp.databinding.ActivityBookingListBinding
import com.example.clinicapp.databinding.ActivityLoginBinding
import com.example.clinicapp.paitent.PatientActivity
import com.example.clinicapp.profile.ProfileFragment

class BookingList : AppCompatActivity() {


    private lateinit var binding: ActivityBookingListBinding

    val items = arrayListOf(
        BookingModel(
            "هالة جودة",
            20,
            "01254789652"
        ),

        BookingModel(
            "دكتور بيشوي ممدوح ",
            40,
            "01254789652"
        ),
        BookingModel(
            "دكتورة تقى  مجدي",
            30
            ,"01254789652"
        ),
        BookingModel(
            "هالة جودة",
            50
            ,"01254789652"
        ),
        BookingModel(
            "هالة جودة",
            200
            ,"01254789652"
        ),

        BookingModel(
            "دكتور بيشوي ممدوح ",
            40
            ,"01254789652"
        ),
        BookingModel(
            "دكتورة تقى  مجدي",
            30
            ,"01254789652"),
        BookingModel(
            "هالة احمد",
            50
            ,"01254789652")
    )
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bookingRecycler.layoutManager = LinearLayoutManager(this)
        binding.bookingRecycler.adapter = BookingListAdapter(items) { item ->


        }
        binding.openChat.setOnClickListener {
            startActivity(Intent(this,PatientActivity::class.java))
        }
        binding.logout.setOnClickListener {
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