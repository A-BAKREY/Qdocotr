package com.example.clinicapp.docotor


import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.clinicapp.R
import com.example.clinicapp.databinding.ActivityDocinfoBinding

class DoctorInformation : Fragment() {

    private var _binding: ActivityDocinfoBinding? = null
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using View Binding
        _binding = ActivityDocinfoBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.layout.setBackgroundColor(requireContext().getColor(R.color.gray))
        val modelReceived = arguments?.getSerializable("data_key") as? DoctorModel
        binding.imageView2.setImageResource(modelReceived!!.image)
        binding.named.text = modelReceived.name
        binding.price.text = modelReceived.price.toString()
        binding.bio.text = modelReceived.bio
        binding.textView2.text = modelReceived.depart

        binding.back.setOnClickListener {
            requireActivity().onBackPressed()
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}