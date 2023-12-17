package com.example.clinicapp.docotor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.clinicapp.R

class DoctorAdapter(private val dataSet: List<DoctorModel>, private val onClick: (DoctorModel) -> Unit) :
    RecyclerView.Adapter<DoctorAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.doctorName)
        val imageView: ImageView = view.findViewById(R.id.doctorImage)


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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_doctor, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = dataSet[position].name
        holder.imageView.setImageResource(dataSet[position].image)
    }

    override fun getItemCount() = dataSet.size
}