package com.example.clinicapp.doctorinfo


import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.clinicapp.R
import com.example.clinicapp.booking.Booking
import com.example.clinicapp.databinding.ActivityDocinfoBinding
import com.example.clinicapp.model.DoctorModel
import com.example.clinicapp.model.DoctorResponseModelItem

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
        binding.exandLayoutBooking.setBackgroundColor(requireContext().getColor(R.color.gray))
        val modelReceived = arguments?.getSerializable("data_key") as DoctorResponseModelItem
        binding.named.text = modelReceived.name
        binding.price.text = modelReceived.price.toString()
        binding.bio.text = modelReceived.price.toString()
        if (modelReceived.department == "1"){
            binding.textView2.text = "الكشف"
        }else if(modelReceived.department == "2"){
            binding.textView2.text = "التاهيل النفسي"
        }else if (modelReceived.department == "3"){
            binding.textView2.text = "التجميل"
        }else{
            binding.textView2.text = "AI"

        }
        binding.backIcon.setOnClickListener {
            requireActivity().onBackPressed()

        }
        binding.appointment.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("name", modelReceived.name)
            bundle.putInt("price", modelReceived.price)


            val secondFragment = Booking().apply {
                arguments = bundle
            }
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, secondFragment)
                    .addToBackStack(null)
                    .commit()
            }

        binding.show.setOnClickListener {
         showDoctorLocation(modelReceived)
        }
        return view
    }
    private fun showDoctorLocation(doctor: DoctorResponseModelItem) {
        val latitude = doctor.latetude
        val longitude = doctor.logtude

        if (latitude != null && longitude != null) {
            val label = "Location Label"
            val uri = "geo:$latitude,$longitude?q=$latitude,$longitude($label)"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            intent.setPackage("com.google.android.apps.maps")

            if (intent.resolveActivity(requireContext().packageManager) != null) {
                startActivity(intent)
            } else {
                // Open Google Maps on a web browser if Google Maps app is not found on the device.
                val webUri =
                    "https://www.google.com/maps/search/?api=1&query=$latitude,$longitude"
                val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(webUri))
                startActivity(webIntent)
                Log.e("lat long", "$latitude$longitude")
            }
        } else {
            // Handle the scenario when location coordinates are empty or null
            Toast.makeText(requireContext(), "Location not available", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    val gmmIntentUri = Uri.parse("geo:$latitude,$longitude?q=$latitude,$longitude")
//    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
//    mapIntent.setPackage("com.google.android.apps.maps")
//    if (mapIntent.resolveActivity(packageManager) != null) {
//        startActivity(mapIntent)
//    } else {
//        // Google Maps app is not installed, handle the situation accordingly
//        Toast.makeText(this, "Google Maps app is not installed", Toast.LENGTH_SHORT).show()
//    }


}