package com.example.clinicapp.department

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import com.example.clinicapp.AiSection.AISection
import com.example.clinicapp.R
import com.example.clinicapp.chat.ChatActivity
import com.example.clinicapp.databinding.ActivityDepartmentBinding
import com.example.clinicapp.department.adapter.DepartmentAdapter
import com.example.clinicapp.department.model.DepartmentModel
import com.example.clinicapp.showdoctor.Doctor

class Department : Fragment() {

    private var _binding: ActivityDepartmentBinding? = null
    private val binding get() = _binding!!

    val items = arrayListOf(
        DepartmentModel(1,R.drawable.profile,"الكشف "),
        DepartmentModel(2,R.drawable.ta,"التاهيل النفسي"),
        DepartmentModel(3,R.drawable.bu,"التجميل "),
        DepartmentModel(4,R.drawable.ai,"ai")

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
            if(position == 3){
                startActivity(Intent(activity,AISection::class.java))
            }else{
                val newFragment = Doctor()
                val bundle = Bundle()
                // هنا يمكنك إرسال أي بيانات إضافية مثل اسم القسم المختار
                bundle.putString("departmentID", items[position].id.toString())
                newFragment.arguments = bundle
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.fragmentContainer, newFragment)
                    ?.addToBackStack(null)
                    ?.commit()
            }

        }
        binding.chat.setOnClickListener {
            startActivity(Intent(requireContext(),ChatActivity::class.java))
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}