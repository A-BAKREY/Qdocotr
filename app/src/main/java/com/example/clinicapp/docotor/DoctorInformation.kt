package com.example.clinicapp.docotor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.clinicapp.R

class DoctorInformation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_docinfo)
        var text = findViewById<TextView>(R.id.named)
        var backbutton = findViewById<TextView>(R.id.button3)
        var image = findViewById<ImageView>(R.id.imageView)
        var depart = findViewById<TextView>(R.id.textView2)
        var price2 = findViewById<Button>(R.id.price)
        var lang1 = findViewById<TextView>(R.id.lange1)
        var lang2 = findViewById<TextView>(R.id.lange2)
        var bioi = findViewById<TextView>(R.id.bio)


        backbutton.setOnClickListener {
            startActivity(Intent(this, Doctor::class.java))
        }
        var name = intent.getStringExtra("name")
        var imagePath = intent.getIntExtra("image",0)
        var department = intent.getStringExtra("Department")
        var bio = intent.getStringExtra("Bio")
        //var price = intent.getIntExtra("price",0)
       // var languege = intent.getStringArrayExtra("language")


        text.text = name
        image.setImageResource(imagePath)
        depart.text = department
        //price2.text = price.toString()
        bioi.text = bio
//        lang1.text = languege!![0]
//        lang2.text = languege!![1]

        }


    }
