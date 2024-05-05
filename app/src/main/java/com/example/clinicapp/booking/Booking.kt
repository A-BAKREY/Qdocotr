package com.example.clinicapp.booking


import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clinicapp.R
import com.example.clinicapp.booking.adapter.BookingAdapter
import com.example.clinicapp.booking.model.BookingModel
import com.example.clinicapp.databinding.ActivityBookingBinding


class Booking : Fragment() {

    private var _binding: ActivityBookingBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: BookingAdapter // Replace MyAdapter with your actual adapter class

    val items = arrayListOf(
        BookingModel(
        "5:00:00pm","12:00:00pm"
    ), BookingModel(
        "6:00:00pm","10:00:00pm"
    )
    )
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using View Binding
        _binding = ActivityBookingBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.exandLayoutBooking.setBackgroundColor(requireContext().getColor(R.color.gray))
        binding.backIcon.setOnClickListener {
            requireActivity().onBackPressed()

        }
        binding.timeRecycler.layoutManager = LinearLayoutManager(requireContext())
        adapter = BookingAdapter(items)
        binding.timeRecycler.adapter = adapter
        binding.doctorName.text = arguments?.getString("name")
        binding.doctorName2.text = arguments?.getString("name")
        binding.price.text = arguments?.getInt("price").toString()
        binding.reservationBtn.setOnClickListener {

        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}