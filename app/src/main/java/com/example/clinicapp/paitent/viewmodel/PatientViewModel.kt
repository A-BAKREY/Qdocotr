package com.example.clinicapp.paitent.viewmodel

import androidx.annotation.Nullable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clinicapp.api.Network
import com.example.clinicapp.model.PatientResponseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PatientViewModel : ViewModel() {

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
        MutableLiveData<PatientResponseModel>()
    val patient: LiveData<PatientResponseModel>
        get() = _patient

    fun getAllPatient() {
        coroutineScope.launch {
            val response = Network.instanceRetrofit.getAllPataint()
            val getAllPatient = response.body()
            try {
                _patient.postValue(getAllPatient)
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