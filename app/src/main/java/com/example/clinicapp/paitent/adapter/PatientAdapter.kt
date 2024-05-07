package com.example.clinicapp.paitent.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.clinicapp.databinding.PatientItemBinding
import com.example.clinicapp.model.PatientResponseModelItem

class PatientAdapter (val context: Context, private val itemSelected: (PatientResponseModelItem) -> Unit): ListAdapter<PatientResponseModelItem, RecyclerView.ViewHolder>(
    diffCallback
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PatientItemBinding.inflate(inflater, parent, false)
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

    private inner class ViewHolder(private val binding: PatientItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ShowToast", "SetTextI18n", "NotifyDataSetChanged")
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(item: PatientResponseModelItem) = with(binding) {
            binding.patientName.text = item.name

            binding.location.setOnClickListener {
                itemSelected(item)
            }
        }
    }
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<PatientResponseModelItem>() {
            override fun areItemsTheSame(
                oldItem: PatientResponseModelItem,
                newItem: PatientResponseModelItem
            ): Boolean {
                return oldItem == newItem
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: PatientResponseModelItem,
                newItem: PatientResponseModelItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}
