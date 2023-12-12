package com.example.clinicapp.department

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView
import com.example.clinicapp.R
import com.example.clinicapp.docotor.Doctor

class Department : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_department)
        val items = arrayListOf(
            DepartmentModel(R.drawable.doctor,"الكشف "),
                           DepartmentModel(R.drawable.ta,"التاهيل النفسي"),
                           DepartmentModel(R.drawable.ai,"ai"),
                           DepartmentModel(R.drawable.bu,"التجميل ")
            )
        val doctorList1 = arrayListOf<DepartmentModel>(
            DepartmentModel(
                R.drawable.doc1,
                "هالة جودة"),

            DepartmentModel(
                R.drawable.doc2,
                "دكتور بيشوي ممدوح "))
        val doctorList2 = arrayListOf(
            DepartmentModel(
                R.drawable.doc3,
                "دكتورة تقى  مجدي"),
            DepartmentModel(
                R.drawable.doc4,
                "هالة جودة")
        )
        val doctorList3 = arrayListOf(
            DepartmentModel(
                R.drawable.doc2,
                "دكتور بيشوي ممدوح "),
            DepartmentModel(
                R.drawable.doc3,
                "دكتورة تقى  مجدي"),
            DepartmentModel(
                R.drawable.doc4,
                "هالة جودة")
        )
        val doctorList4 = arrayListOf(
            DepartmentModel(
                R.drawable.doc1,
                "هالة جودة"),

            DepartmentModel(
                R.drawable.doc2,
                "دكتور بيشوي ممدوح "),
            DepartmentModel(
                R.drawable.doc3,
                "دكتورة تقى  مجدي"),
            DepartmentModel(
                R.drawable.doc4,
                "هالة جودة")
        )
        var departmentGrid = findViewById<GridView>(R.id.departmentGrid)
        var adapter = DepartmentAdapter(this,items)
        departmentGrid.adapter = adapter
        departmentGrid.onItemClickListener = AdapterView.OnItemClickListener{
            _,_,positon,_ ->
            val item = departmentGrid.getItemAtPosition(positon)
            if (positon == 0){
                var intent = Intent(this,Doctor::class.java)
                intent.putExtra("doctorlist",doctorList1)
                startActivity(intent)
            }else if (positon == 1 ){
                var intent = Intent(this,Doctor::class.java)
                intent.putExtra("doctorlist",doctorList2)
                startActivity(intent)
            }else if (positon == 2){
                var intent = Intent(this,Doctor::class.java)
                intent.putExtra("doctorlist",doctorList3)
                startActivity(intent)
            }else{
                var intent = Intent(this,Doctor::class.java)
                intent.putExtra("doctorlist",doctorList4)
                startActivity(intent)
            }
            startActivity(Intent(this,Doctor::class.java))
        }

    }
}