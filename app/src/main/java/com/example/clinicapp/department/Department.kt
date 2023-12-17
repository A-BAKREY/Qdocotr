package com.example.clinicapp.department

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.clinicapp.R
import com.example.clinicapp.databinding.ActivityDepartmentBinding
import com.example.clinicapp.docotor.Doctor

class Department : Fragment() {

    private var _binding: ActivityDepartmentBinding? = null
    private val binding get() = _binding!!

    val items = arrayListOf(
        DepartmentModel(R.drawable.profile,"الكشف "),
        DepartmentModel(R.drawable.ta,"التاهيل النفسي"),
        DepartmentModel(R.drawable.ai,"ai"),
        DepartmentModel(R.drawable.bu,"التجميل ")
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityDepartmentBinding.inflate(inflater, container, false)
        val view = binding.root
        val adapter = DepartmentAdapter(requireContext(),items)
        binding.departmentGrid.adapter = adapter
        val newFragment = Doctor()
        binding.departmentGrid.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainer, newFragment)
                ?.addToBackStack(null)
                ?.commit()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}