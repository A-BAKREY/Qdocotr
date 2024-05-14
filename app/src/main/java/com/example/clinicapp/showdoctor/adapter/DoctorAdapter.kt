package com.example.clinicapp.showdoctor.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.clinicapp.databinding.ItemDoctorBinding
import com.example.clinicapp.model.DoctorResponseModelItem

class DoctorAdapter (val context: Context,
                     private val itemSelected: (DoctorResponseModelItem) -> Unit):
    ListAdapter<DoctorResponseModelItem, RecyclerView.ViewHolder>(
    diffCallback
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDoctorBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {

        getItem(position)?.let {
            (holder as ViewHolder).bind(it)

        }
    }

    private inner class ViewHolder(private val binding: ItemDoctorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ShowToast", "SetTextI18n", "NotifyDataSetChanged")
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(item: DoctorResponseModelItem) = with(binding) {
            binding.doctorName.text = item.name
            binding.bio.text = item.price.toString()
            binding.layout.setOnClickListener {
                itemSelected.invoke(item)
            }
        }
    }
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<DoctorResponseModelItem>() {
            override fun areItemsTheSame(
                oldItem: DoctorResponseModelItem,
                newItem: DoctorResponseModelItem
            ): Boolean {
                return oldItem == newItem
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: DoctorResponseModelItem,
                newItem: DoctorResponseModelItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}
