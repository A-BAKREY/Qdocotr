package com.example.clinicapp.Bookinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clinicapp.R
import com.example.clinicapp.databinding.ActivityBookingListBinding

class BookingList : AppCompatActivity() {

    private var _binding: ActivityBookingListBinding? = null
    private val binding get() = _binding!!
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
        setContentView(R.layout.activity_booking_list)
        val recycler = findViewById<RecyclerView>(R.id.bookingRecycler)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = BookingListAdapter(items) { item ->
        }
    }
}