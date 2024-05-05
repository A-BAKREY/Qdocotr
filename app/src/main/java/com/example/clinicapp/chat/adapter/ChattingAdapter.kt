package com.example.clinicapp.chat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.clinicapp.R
import com.example.clinicapp.chat.model.RepliesModel


class ChattingAdapter (private var dataSet: List<RepliesModel>) :
    RecyclerView.Adapter<ChattingAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView1: TextView = view.findViewById(R.id.content)
        val img: ImageView = view.findViewById(R.id.image)
        val textView2: TextView = view.findViewById(R.id.messageDate)
        val textView3: TextView = view.findViewById(R.id.senderName)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recive_message, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView1.text = dataSet[position].message
        holder.textView2.text = dataSet[position].date
        holder.textView3.text = "Guest"
        holder.img.setImageResource(R.drawable.man)
    }

    override fun getItemCount() = dataSet.size
}