package com.example.clinicapp.Bookinglist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clinicapp.Bookinglist.model.BookingModel
import com.example.clinicapp.Bookinglist.viewmodel.adapter.BookingListAdapter
import com.example.clinicapp.R
import com.example.clinicapp.databinding.ActivityBookingListBinding
import com.example.clinicapp.databinding.ActivityLoginBinding
import com.example.clinicapp.paitent.PatientActivity

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
    }
}