package com.example.clinicapp.model

data class LoginResponseModel(
    val doctors: Any,
    val email: String,
    val id: Int,
    val name: String,
    val password: String,
    val pataints: Any,
    val type: String,
    val message:String
)