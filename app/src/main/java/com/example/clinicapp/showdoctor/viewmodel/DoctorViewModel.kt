package com.example.clinicapp.showdoctor.viewmodel

import androidx.annotation.Nullable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clinicapp.api.Network
import com.example.clinicapp.model.DoctorResponseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DoctorViewModel: ViewModel() {

    private val job = Job()
    private val coroutineScope = CoroutineScope(job + Dispatchers.IO)

    private val _snackBar = MutableLiveData<Boolean>()

    private val _snackBarWithMessage = MutableLiveData<String>()
    val snackBarWithMessage: LiveData<String>
        get() = _snackBarWithMessage

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading
    private val _patient =
        MutableLiveData<DoctorResponseModel>()
    val patient: LiveData<DoctorResponseModel>
        get() = _patient

    fun getAllDoctors(department: String) {
        coroutineScope.launch {
            val response = Network.instanceRetrofit.getAllDoctors(department)
            val getAllDoctor = response.body()
            try {
                _patient.postValue(getAllDoctor)
                _loading.postValue(false)
            } catch (e: Exception) {
                e.printStackTrace()
                shouldShowSnackBar("an error happened try again")
                _loading.postValue(false)
            }
        }

    }
    fun getAllDoctor() {
        coroutineScope.launch {
            val response = Network.instanceRetrofit.getAllDoctor()
            val getAllDoctor = response.body()
            try {
                _patient.postValue(getAllDoctor)
                _loading.postValue(false)
            } catch (e: Exception) {
                e.printStackTrace()
                shouldShowSnackBar("an error happened try again")
                _loading.postValue(false)
            }
        }

    }
    private fun shouldShowSnackBar(@Nullable message: String?) {
        if (message == null) _snackBar.postValue(true) else _snackBarWithMessage.postValue(message)
    }

}