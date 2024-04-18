package com.example.clinicapp.booking.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.clinicapp.R
import com.example.clinicapp.booking.model.BookingModel


class BookingAdapter (private var dataSet: List<BookingModel>) :
    RecyclerView.Adapter<BookingAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView1: TextView = view.findViewById(R.id.fromTime)
        val textView2: TextView = view.findViewById(R.id.toTime)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.reservation_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookingAdapter.MyViewHolder, position: Int) {
        holder.textView1.text = dataSet[position].from
        holder.textView2.text = dataSet[position].to

    }

    override fun getItemCount() = dataSet.size
}