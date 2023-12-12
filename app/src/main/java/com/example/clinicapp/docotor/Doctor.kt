package com.example.clinicapp.docotor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView
import com.example.clinicapp.R
import com.example.clinicapp.department.DepartmentAdapter
import com.example.clinicapp.department.DepartmentModel

class Doctor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctors)
        val language= listOf("english,arabic")
        val items = listOf(
            DoctorModel(
                R.drawable.doc1,
                "هالة جودة",
                200
                ,"د. هالة جودة استشاري الصحة النفسية. أطفال - مراهقين -تنمية مهارات وتعديل سلوك وتطبيق اختبار الذكاء والاختبارات النفسية و العلاج المعرفي السلوكي، والإرشاد الأسري",
                "استشاري الصحة النفسية و الأرشاد الأسري"),

            DoctorModel(
                R.drawable.doc2,
                "دكتور بيشوي ممدوح ",
                400
                ,"استشاري الأمراض النفسية بالغين ومسنين. خبير طب النوم الإكلينيكي الزمالة البريطانية في الطب النفسي من الكلية الملكية بإنجلترا ماجستير أمراض المخ والأعصاب والطب النفسي- جامعة عين شمس. البورد الأوروبي في طب النوم - الجمعية الأوروبية لأبحاث النوم. متخصص في اضطرابات النوم كالأرق، فرط النوم ، متلازمة تململ الساقين و توقف التنفس أثناء النوم. متخصص في اضطرابات كهرباء المخ(الصرع) واضطرابات الذاكرة و القلق والاكتئاب والوسواس القهري ونوبات الهلع و الفصام وثنائي القطب. متخصص في علاج إدمان المخدرات",
                "أخصائي أمراض المخ والأعصاب والطب النفسي وطب النوم"),
            DoctorModel(
                R.drawable.doc3,
                "دكتورة تقى  مجدي",
                300
                ,"د. هالة جودة استشاري الصحة النفسية. أطفال - مراهقين -تنمية مهارات وتعديل سلوك وتطبيق اختبار الذكاء والاختبارات النفسية و العلاج المعرفي السلوكي، والإرشاد الأسري",

                "استشاري الصحة النفسية و الأرشاد الأسري"),
            DoctorModel(
                R.drawable.doc4,
                "هالة جودة",
                500
                ,"د. هالة جودة استشاري الصحة النفسية. أطفال - مراهقين -تنمية مهارات وتعديل سلوك وتطبيق اختبار الذكاء والاختبارات النفسية و العلاج المعرفي السلوكي، والإرشاد الأسري",
                "استشاري الصحة النفسية و الأرشاد الأسري")
        )
        val items2 = listOf(
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
        val list1 = intent.getSerializableExtra("doctorlist") as ArrayList<DepartmentModel>
        var doctorGrid = findViewById<GridView>(R.id.doctorGrid)
        var adapter = DepartmentAdapter(this,list1)
        doctorGrid.adapter = adapter
        doctorGrid.onItemClickListener = AdapterView.OnItemClickListener{
                _,_,positon,_ ->
            val item = doctorGrid.getItemAtPosition(positon)
            var intent = Intent(this,DoctorInformation::class.java)
            intent.putExtra("name",items[positon].name)
            intent.putExtra("Department",items[positon].depart)
            intent.putExtra("Bio",items[positon].bio)
            intent.putExtra("image",items[positon].image)
            startActivity(intent)


        }

    }
}
