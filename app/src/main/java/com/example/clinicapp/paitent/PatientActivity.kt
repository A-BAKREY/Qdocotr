package com.example.clinicapp.paitent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clinicapp.Bookinglist.BookingList
import com.example.clinicapp.chat.ChatActivity
import com.example.clinicapp.databinding.ActivityPatientBinding
import com.example.clinicapp.model.PatientResponseModel
import com.example.clinicapp.paitent.adapter.PatientAdapter
import com.example.clinicapp.paitent.viewmodel.PatientViewModel

class PatientActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPatientBinding
    private lateinit var viewModel: PatientViewModel

    private val patientAdapter: PatientAdapter by lazy {
        PatientAdapter(this, itemSelected = { item ->
            startActivity(Intent(this,ChatActivity::class.java))
        })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPatientBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(PatientViewModel::class.java)
        binding.doctorRecycler.layoutManager = LinearLayoutManager(this)

        setListener()
        setObserver()
        getAllPatient()
    }
    private fun getAllPatient() {
        viewModel.getAllPatient()
    }
    private fun setObserver(){
        viewModel.loading.observe(this, Observer { registerResult ->
            registerResult ?: return@Observer
            if (registerResult) {
                binding.mainProgress.visibility = View.VISIBLE
                dimBackground()
            } else {
                binding.mainProgress.visibility = View.GONE
                clearDimBackground()
            }
        })
        viewModel.patient.observe(this, Observer { patientResult ->
            patientResult ?: return@Observer // handle null case if needed
            initPatientStatusRequest(patientResult)
        })
    }
    private fun dimBackground() {
        val window = this.window
        val layoutParams = window.attributes
        layoutParams.alpha = 0.6f // يمكن تعديل هذه القيمة لتغيير مستوى التعتيم
        window.attributes = layoutParams
    }

    private fun clearDimBackground() {
        val window = this.window
        val layoutParams = window.attributes
        layoutParams.alpha = 1.0f // استعادة الشفافية الكاملة
        window.attributes = layoutParams
    }
    private fun setListener() {
        binding.arrowback.setOnClickListener {
            startActivity(Intent(this, BookingList::class.java))
        }

    }
    private fun initPatientStatusRequest(data: PatientResponseModel?) {
        binding.doctorRecycler.adapter = patientAdapter
        patientAdapter.submitList(data)
    }
}