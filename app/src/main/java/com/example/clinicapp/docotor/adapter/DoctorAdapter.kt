package com.example.clinicapp.docotor.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.clinicapp.R
import com.example.clinicapp.docotor.model.DoctorModel

class DoctorAdapter(private var dataSet: List<DoctorModel>, private val onClick: (DoctorModel) -> Unit) :
    RecyclerView.Adapter<DoctorAdapter.MyViewHolder>() {

    private var filteredList: MutableList<DoctorModel> = dataSet.toMutableList()

    private val buttonActions: MutableMap<Int, () -> Unit> = mutableMapOf()

    // Function to set button actions
    fun setButtonAction(buttonId: Int, action: () -> Unit) {
        buttonActions[buttonId] = action
    }
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.doctorName)
        val imageView: ImageView = view.findViewById(R.id.doctorImage)
        val appointment: Button = view.findViewById(R.id.appointment)
        init {
            view.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onClick(filteredList[position])
                }
            }
        }
    }
    fun updateData(newData: List<DoctorModel>) {
        dataSet = newData
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        filteredList.clear()
        if (query.isEmpty()) {
            filteredList.addAll(dataSet)
        } else {
            val filterPattern = query.toLowerCase().trim()
            dataSet.forEach { item ->
                if (item.name.toLowerCase().contains(filterPattern)) {
                    filteredList.add(item)
                }
            }
        }
        notifyDataSetChanged() // Notify RecyclerView of data changes
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_doctor, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = filteredList[position].name
        holder.imageView.setImageResource(filteredList[position].image)
        holder.appointment.setOnClickListener {
            val action = buttonActions[holder.appointment.id]
            action?.invoke()

        }
    }

    override fun getItemCount() = filteredList.size
}