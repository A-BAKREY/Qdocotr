package com.example.clinicapp.authantication.viewmodel

import androidx.annotation.Nullable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clinicapp.api.Network
import com.example.clinicapp.model.LoginData
import com.example.clinicapp.model.LoginResponseModel
import com.example.clinicapp.model.NewLoginModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AuthanicationViewModel : ViewModel() {

    private val _snackBar = MutableLiveData<Boolean>()

    private val _snackBarWithMessage = MutableLiveData<String>()
    val snackBarWithMessage: LiveData<String>
        get() = _snackBarWithMessage
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _loginResult = MutableLiveData<LoginResponseModel>()
    val loginResult: LiveData<LoginResponseModel> = _loginResult

    fun login(email: String, password: String) {

        viewModelScope.launch(Dispatchers.IO) {
            _loading.postValue(true)
            val response = Network.instanceRetrofit.login(LoginData(email, password))
            val loginResponse = response.body()
            try {
                if (loginResponse != null) {
                    _loginResult.postValue(loginResponse)
                } else {
                    shouldShowSnackBar("an error happened try again")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                shouldShowSnackBar("an error happened try again")
                _loginResult.postValue(null)
            }
            _loading.postValue(false) // Move this outside the try-catch block
        }
    }

    private fun shouldShowSnackBar(@Nullable message: String?) {
        if (message == null) _snackBar.postValue(true) else _snackBarWithMessage.postValue(message)
    }

}