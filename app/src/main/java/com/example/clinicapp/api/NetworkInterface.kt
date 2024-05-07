package com.example.clinicapp.api

import com.example.clinicapp.model.DoctorResponseModel
import com.example.clinicapp.model.LoginData
import com.example.clinicapp.model.LoginResponseModel
import com.example.clinicapp.model.PatientResponseModel
import com.example.clinicapp.model.RegisterModel
import com.example.clinicapp.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface NetworkInterface {
    @POST("api/Users/login")
    suspend fun login(@Body loginData: LoginData): Response<LoginResponseModel>

    @POST("api/Users/Register")
    suspend fun register(@Body registerModel: RegisterModel): Response<RegisterResponse>

    @GET("api/Doctors")
    suspend fun getAllDoctors(@Query("department") dep:String): Response<DoctorResponseModel>

    @GET("api/Pataints")
    suspend fun getAllPataint(): Response<PatientResponseModel>

    @GET("api/Doctors/GetAll")
    suspend fun getAllDoctor(): Response<DoctorResponseModel>

}