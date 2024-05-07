package com.example.clinicapp.authantication.viewmodel

import androidx.annotation.Nullable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clinicapp.api.Network
import com.example.clinicapp.model.RegisterModel
import com.example.clinicapp.model.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignupViewModel: ViewModel() {

    private val _snackBar = MutableLiveData<Boolean>()

    private val _snackBarWithMessage = MutableLiveData<String>()
    val snackBarWithMessage: LiveData<String>
        get() = _snackBarWithMessage

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading
    private val _register =
        MutableLiveData<RegisterResponse>()
    val register: LiveData<RegisterResponse>
        get() = _register

    fun register(registerModel: RegisterModel) {

        viewModelScope.launch(Dispatchers.IO) {
            _loading.postValue(true)
            val response = Network.instanceRetrofit.register(registerModel)
            val registerResponse = response.body()
            try {
                if (registerResponse?.code == 1) {
                    _register.postValue(registerResponse)
                } else {
                    shouldShowSnackBar("an error happened try again")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                shouldShowSnackBar("an error happened try again")
                _register.postValue(null)
            }
            _loading.postValue(false) // Move this outside the try-catch block
        }
    }
    private fun shouldShowSnackBar(@Nullable message: String?) {
        if (message == null) _snackBar.postValue(true) else _snackBarWithMessage.postValue(message)
    }

}
