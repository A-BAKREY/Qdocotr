package com.example.clinicapp.model

data class PatientResponseModelItem(
    val address: String,
    val id: Int,
    val name: String,
    val phoneNumber: String,
    val user: Any,
    val userId: Int
)