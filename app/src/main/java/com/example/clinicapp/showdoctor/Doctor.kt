package com.example.clinicapp.showdoctor

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clinicapp.R
import com.example.clinicapp.databinding.ActivityDoctorsBinding
import com.example.clinicapp.doctorinfo.DoctorInformation
import com.example.clinicapp.model.DoctorModel
import com.example.clinicapp.model.DoctorResponseModelItem
import com.example.clinicapp.paitent.viewmodel.PatientViewModel
import com.example.clinicapp.showdoctor.adapter.DoctorAdapter
import com.example.clinicapp.showdoctor.viewmodel.DoctorViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class Doctor : Fragment() {

    private lateinit var viewModel: DoctorViewModel
    private lateinit var resultList: ArrayList<DoctorResponseModelItem>
    private var _binding: ActivityDoctorsBinding? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var currentLat = 0.0
    private var currentLon = 0.0
    private var nearestDoctor: DoctorResponseModelItem? = null
    private var departmentID: String? = null


    private val binding get() = _binding!!
    private val doctorAdapter: DoctorAdapter by lazy { DoctorAdapter(requireContext(), itemSelected = { item ->
        val bundle = Bundle().apply { putSerializable("data_key", item) }
        val secondFragment = DoctorInformation().apply { arguments = bundle }
        replaceFragment(secondFragment)
    }) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityDoctorsBinding.inflate(inflater, container, false)
        val view = binding.root
        resultList = arrayListOf()
        viewModel = ViewModelProvider(requireActivity()).get(DoctorViewModel::class.java)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        arguments?.let {
            departmentID = it.getString("departmentID")
        }
        viewModel.getAllDoctors(departmentID!!)
        Log.e("dep id",departmentID.toString())
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            requestLocationPermission()
        } else {
            getCurrentLocation()
        }
        setObserver()

        return view
    }

    private fun performSearch(query: String) {
        val filteredList = arrayListOf<DoctorResponseModelItem>()

        for (i in resultList.indices) {
            if (resultList[i].name.startsWith(query, ignoreCase = true)) {
                filteredList.add(resultList[i])
            }
        }
        doctorAdapter.submitList(filteredList)
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_LOCATION_PERMISSION
        )
    }

    private fun getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                location?.let {
                    currentLat = it.latitude
                    currentLon = it.longitude
                    Log.e("location doctor", "$currentLat$currentLon")
                }
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.findDoc.setOnClickListener {
            nearestDoctor = findNearestDoctor(currentLat, currentLon, resultList)
            if (nearestDoctor != null) {
                val nearestDoctorList = ArrayList<DoctorResponseModelItem>()
                nearestDoctorList.add(nearestDoctor!!)
                performSearch(nearestDoctor!!.name)
            } else {
                Toast.makeText(requireContext(), "لم يتم العثور على دكتور قريب", Toast.LENGTH_SHORT).show()
            }
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                performSearch(newText)
                return false
            }
        })
        binding.doctorRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.doctorRecycler.adapter = doctorAdapter
    }

    private fun replaceFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun distance(
        lat1: Double, lon1: Double,
        lat2: Double, lon2: Double
    ): Double {
        val R = 6371
        val latDistance = Math.toRadians(lat2 - lat1)
        val lonDistance = Math.toRadians(lon2 - lon1)
        val a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2)
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        return R * c
    }

    private fun findNearestDoctor(currentLat: Double, currentLon: Double, doctors: ArrayList<DoctorResponseModelItem>): DoctorResponseModelItem? {
        var closestDoctor: DoctorResponseModelItem? = null
        var minDistance = Double.MAX_VALUE

        for (doctor in doctors) {
            val distance = distance(currentLat, currentLon, doctor.latetude, doctor.logtude)
            if (distance < minDistance) {
                minDistance = distance
                closestDoctor = doctor
            }
        }

        return closestDoctor
    }

    private fun getAllDoctors() {
            viewModel.getAllDoctor()
    }

    private fun setObserver() {
        viewModel.loading.observe(requireActivity(), Observer { isLoading ->
            isLoading ?: return@Observer
            val binding = _binding ?: return@Observer
            if (isLoading) {
                binding.mainProgress.visibility = View.VISIBLE
                dimBackground()
            } else {
                binding.mainProgress.visibility = View.GONE
                clearDimBackground()
            }
        })
        viewModel.patient.observe(requireActivity(), Observer { patientResult ->
            patientResult ?: return@Observer
            initDoctorsRequest(patientResult)
        })
    }

    private fun dimBackground() {
        val window = requireActivity().window
        val layoutParams = window.attributes
        layoutParams.alpha = 0.6f
        window.attributes = layoutParams
    }

    private fun clearDimBackground() {
        val window = requireActivity().window
        val layoutParams = window.attributes
        layoutParams.alpha = 1.0f
        window.attributes = layoutParams
    }

    private fun initDoctorsRequest(data: List<DoctorResponseModelItem>) {
        resultList.clear()
        resultList.addAll(data)
        doctorAdapter.submitList(resultList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val REQUEST_LOCATION_PERMISSION = 1
    }
}