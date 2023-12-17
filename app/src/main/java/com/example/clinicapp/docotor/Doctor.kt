package com.example.clinicapp.docotor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clinicapp.R
import com.example.clinicapp.databinding.ActivityDoctorsBinding

class Doctor : Fragment() {

    private var _binding: ActivityDoctorsBinding? = null
    private val binding get() = _binding!!

    val items = arrayListOf(
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
            "استشاري الصحة النفسية و الأرشاد الأسري"),  DoctorModel(
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using View Binding
        _binding = ActivityDoctorsBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.doctorRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.doctorRecycler.adapter = DoctorAdapter(items) { item ->
            val bundle = Bundle().apply {
                putSerializable("data_key", item)
            }

            val secondFragment = DoctorInformation().apply {
                arguments = bundle
            }
            replaceFragment(secondFragment)

        }
        return view
    }
    private fun replaceFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}