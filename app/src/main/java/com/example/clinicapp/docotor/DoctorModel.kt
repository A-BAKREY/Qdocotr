package com.example.clinicapp.docotor

import android.media.Image
import java.io.Serializable

data class DoctorModel(
    var image: Int,
    var name: String,
    var price: Int,
    var bio: String,
    var depart: String,
): Serializable
