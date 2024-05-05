package com.example.clinicapp.Bookinglist.viewmodel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.clinicapp.Bookinglist.model.BookingModel
import com.example.clinicapp.R

class BookingListAdapter(private val dataSet: List<BookingModel>, private val onClick: (BookingModel) -> Unit) :
    RecyclerView.Adapter<BookingListAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val age: TextView = view.findViewById(R.id.age)
        val number: TextView = view.findViewById(R.id.number)

        init {
            view.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onClick(dataSet[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_booking_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = dataSet[position].name
        holder.age.text = dataSet[position].age.toString()
        holder.number.text = dataSet[position].number.toString()

    }

    override fun getItemCount() = dataSet.size
}