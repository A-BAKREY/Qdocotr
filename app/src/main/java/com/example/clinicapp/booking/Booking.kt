package com.example.clinicapp.booking


import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.clinicapp.R
import com.example.clinicapp.databinding.ActivityBookingBinding


class Booking : Fragment() {

    private var _binding: ActivityBookingBinding? = null
    private val binding get() = _binding!!

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
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}