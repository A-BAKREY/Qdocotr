package com.example.clinicapp.model

import java.io.Serializable

data class DoctorResponseModelItem(
    val address: String,
    val department: String,
    val id: Int,
    val iserId: Int,
    val latetude: Double,
    val logtude: Double,
    val name: String,
    val price: Int,
    val user: Any
) : Serializable