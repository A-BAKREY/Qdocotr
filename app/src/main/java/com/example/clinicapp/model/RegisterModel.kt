package com.example.clinicapp.model

data class RegisterModel(
    val address: String,
    val department: String,
    val email: String,
    val latetude: Double,
    val logtude: Double,
    val name: String,
    val password: String,
    val phoneNumber: String,
    val price: Int,
    val type: String
)