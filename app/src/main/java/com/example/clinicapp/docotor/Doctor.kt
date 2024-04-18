package com.example.clinicapp.docotor

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clinicapp.R
import com.example.clinicapp.databinding.ActivityDoctorsBinding
import com.example.clinicapp.docotor.adapter.DoctorAdapter
import com.example.clinicapp.docotor.model.DoctorModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class Doctor : Fragment() {

    private var _binding: ActivityDoctorsBinding? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    var currentLat = 0.0
    var currentLon = 0.0
    private var nearestDoctor: DoctorModel? = null

    private val binding get() = _binding!!
    private lateinit var adapter: DoctorAdapter // Replace MyAdapter with your actual adapter class

    val items = arrayListOf(
        DoctorModel(
            1,
            R.drawable.doc1,
            "هالة جودة",
            200
            ,"د. هالة جودة استشاري الصحة النفسية. أطفال - مراهقين -تنمية مهارات وتعديل سلوك وتطبيق اختبار الذكاء والاختبارات النفسية و العلاج المعرفي السلوكي، والإرشاد الأسري",
            "استشاري الصحة النفسية و الأرشاد الأسري",
            30.128611,
            31.242222
        ),

        DoctorModel(
            2,
            R.drawable.doc2,
            "دكتور بيشوي ممدوح ",
            400
            ,"استشاري الأمراض النفسية بالغين ومسنين. خبير طب النوم الإكلينيكي الزمالة البريطانية في الطب النفسي من الكلية الملكية بإنجلترا ماجستير أمراض المخ والأعصاب والطب النفسي- جامعة عين شمس. البورد الأوروبي في طب النوم - الجمعية الأوروبية لأبحاث النوم. متخصص في اضطرابات النوم كالأرق، فرط النوم ، متلازمة تململ الساقين و توقف التنفس أثناء النوم. متخصص في اضطرابات كهرباء المخ(الصرع) واضطرابات الذاكرة و القلق والاكتئاب والوسواس القهري ونوبات الهلع و الفصام وثنائي القطب. متخصص في علاج إدمان المخدرات",
            "أخصائي أمراض المخ والأعصاب والطب النفسي وطب النوم",
            30.033333,
            31.233334
        ),
        DoctorModel(
            3,
            R.drawable.doc3,
            "دكتورة تقى  مجدي",
            300
            ,"د. هالة جودة استشاري الصحة النفسية. أطفال - مراهقين -تنمية مهارات وتعديل سلوك وتطبيق اختبار الذكاء والاختبارات النفسية و العلاج المعرفي السلوكي، والإرشاد الأسري",

            "استشاري الصحة النفسية و الأرشاد الأسري",
            29.952654,
            30.921919
        ),
        DoctorModel(
            4,
            R.drawable.doc4,
            "هالة جودة",
            500
            ,"د. هالة جودة استشاري الصحة النفسية. أطفال - مراهقين -تنمية مهارات وتعديل سلوك وتطبيق اختبار الذكاء والاختبارات النفسية و العلاج المعرفي السلوكي، والإرشاد الأسري",
            "استشاري الصحة النفسية و الأرشاد الأسري",
            31.037933,
            31.381523
        ),  DoctorModel(
            5,
            R.drawable.doc1,
            "هالة جودة",
            200
            ,"د. هالة جودة استشاري الصحة النفسية. أطفال - مراهقين -تنمية مهارات وتعديل سلوك وتطبيق اختبار الذكاء والاختبارات النفسية و العلاج المعرفي السلوكي، والإرشاد الأسري",
            "استشاري الصحة النفسية و الأرشاد الأسري",
            30.013056,
            31.208853
        ),

        DoctorModel(
            6,
            R.drawable.doc2,
            "دكتور بيشوي ممدوح ",
            400
            ,"استشاري الأمراض النفسية بالغين ومسنين. خبير طب النوم الإكلينيكي الزمالة البريطانية في الطب النفسي من الكلية الملكية بإنجلترا ماجستير أمراض المخ والأعصاب والطب النفسي- جامعة عين شمس. البورد الأوروبي في طب النوم - الجمعية الأوروبية لأبحاث النوم. متخصص في اضطرابات النوم كالأرق، فرط النوم ، متلازمة تململ الساقين و توقف التنفس أثناء النوم. متخصص في اضطرابات كهرباء المخ(الصرع) واضطرابات الذاكرة و القلق والاكتئاب والوسواس القهري ونوبات الهلع و الفصام وثنائي القطب. متخصص في علاج إدمان المخدرات",
            "أخصائي أمراض المخ والأعصاب والطب النفسي وطب النوم",
            30.005493,
            31.477898),
        DoctorModel(
            7,
            R.drawable.doc3,
            "toqa",
            300
            ,"د. هالة جودة استشاري الصحة النفسية. أطفال - مراهقين -تنمية مهارات وتعديل سلوك وتطبيق اختبار الذكاء والاختبارات النفسية و العلاج المعرفي السلوكي، والإرشاد الأسري",

            "استشاري الصحة النفسية و الأرشاد الأسري",
            31.205753,
            29.924526),
        DoctorModel(
            8,
            R.drawable.doc4,
            "هالة احمد",
            500
            ,"د. هالة جودة استشاري الصحة النفسية. أطفال - مراهقين -تنمية مهارات وتعديل سلوك وتطبيق اختبار الذكاء والاختبارات النفسية و العلاج المعرفي السلوكي، والإرشاد الأسري",
            "استشاري الصحة النفسية و الأرشاد الأسري",
            29.307384099805844,
            30.839889600000003)
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using View Binding
        _binding = ActivityDoctorsBinding.inflate(inflater, container, false)
        val view = binding.root

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        // تحقق مما إذا كان لديك الإذن للوصول إلى الموقع، إذا لم يكن قم بطلب الإذن
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            requestLocationPermission()
        } else {
            // قم بالحصول على الموقع الحالي
            getCurrentLocation()
        }

        return view
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
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                // استخدم location هنا، حيث أنه يمثل الموقع الحالي
                 currentLat = location.latitude
                 currentLon = location.longitude
                Log.e("location doctor",currentLat.toString()+currentLon.toString())
                // يمكنك استخدام الموقع هنا بالطريقة التي تريدها
            }
            .addOnFailureListener { e ->
                // حدث خطأ أثناء الحصول على الموقع الحالي
                e.printStackTrace()
            }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = DoctorAdapter(items) { item ->
            val bundle = Bundle().apply {
                putSerializable("data_key", item as DoctorModel)
            }

            val secondFragment = DoctorInformation().apply {
                arguments = bundle
            }
            replaceFragment(secondFragment)
        }


        binding.findDoc.setOnClickListener {
            nearestDoctor = findNearestDoctor(currentLat, currentLon, items)
            if (nearestDoctor != null) {
                // إضافة الطبيب الأقرب إلى ArrayList جديدة
                val nearestDoctorList = ArrayList<DoctorModel>()
                nearestDoctorList.add(nearestDoctor!!)

                // تحديث القائمة المعروضة في RecyclerView
                adapter.filter(nearestDoctor!!.name)

                // يمكنك إظهار رسالة تأكيد أو إشعار للمستخدم هنا
            } else {
                Toast.makeText(requireContext(), "لم يتم العثور على دكتور قريب", Toast.LENGTH_SHORT).show()
            }
        }



        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                Log.d("SearchFragment", "New query: $newText")
                adapter.filter(newText)
                return false
            }
        })
        binding.doctorRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.doctorRecycler.adapter = adapter // Use the initialized adapter here
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
        val R = 6371 // نصف قطر الأرض بالكيلومترات
        val latDistance = Math.toRadians(lat2 - lat1)
        val lonDistance = Math.toRadians(lon2 - lon1)
        val a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2)
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        val distance = R * c
        Log.d("distance of doctor", distance.toString())

        return distance
    }
    private fun findNearestDoctor(currentLat: Double, currentLon: Double, doctors: List<DoctorModel>): DoctorModel? {
        var closestDoctor: DoctorModel? = null
        var minDistance = Double.MAX_VALUE

        for (doctor in doctors) {
            val distance = distance(currentLat, currentLon, doctor.docLAT, doctor.docLON)
            if (distance < minDistance) {
                minDistance = distance
                closestDoctor = DoctorModel(doctor.id,doctor.image,doctor.name,doctor.price,doctor.bio,doctor.depart,doctor.docLAT,doctor.docLON)
            }
        }

        return closestDoctor
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val REQUEST_LOCATION_PERMISSION = 1
    }
}