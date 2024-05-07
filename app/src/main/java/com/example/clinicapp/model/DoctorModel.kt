package com.example.clinicapp.model

import java.io.Serializable

data class DoctorModel(
    val id: Int,
    var image: Int,
    var name: String,
    var price: Int,
    var bio: String,
    var depart: String,
    var docLAT: Double,
    var docLON: Double,

    ): Serializable
