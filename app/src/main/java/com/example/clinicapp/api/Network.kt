package com.example.clinicapp.api

import com.example.clinicapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val okHttpClient: OkHttpClient = OkHttpClient.Builder().addInterceptor(
    run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }
).readTimeout(500, TimeUnit.SECONDS)
    .writeTimeout(500, TimeUnit.SECONDS)
    .connectTimeout(500, TimeUnit.SECONDS).build()

var retrofit: Retrofit? = null

fun createRetrofit(baseUrl: String): NetworkInterface {
    if (retrofit == null) {
        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
    }
    return retrofit!!.create(NetworkInterface::class.java)
}

object NetworkForViewModel {
    var retrofitService: NetworkInterface =
        createRetrofit("")
}