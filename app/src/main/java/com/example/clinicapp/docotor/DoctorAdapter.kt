package com.example.clinicapp.docotor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.clinicapp.R

class DoctorAdapter(private val context: Context, private val item: List<DoctorModel>) :
    BaseAdapter() {
    override fun getCount(): Int {
        return item.size
    }

    override fun getItem(p0: Int): Any {
        return item[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.item_doctor,p2,false)
        var image = view?.findViewById<ImageView>(R.id.doctorImage)
        var name = view?.findViewById<TextView>(R.id.doctorName)
        var bio = view?.findViewById<Button>(R.id.bio)
        var appoint = view?.findViewById<Button>(R.id.appointment)
        val items = item[p0]
        image?.setImageResource(items.image)
        name?.text = items.name
        bio?.setText("Bio")
        appoint?.text = "Appointment"
        return view
    }
}
