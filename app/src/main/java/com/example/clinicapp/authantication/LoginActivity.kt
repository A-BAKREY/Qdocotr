package com.example.clinicapp.authantication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.clinicapp.Bookinglist.BookingList
import com.example.clinicapp.MainActivity
import com.example.clinicapp.admin.Admin
import com.example.clinicapp.authantication.viewmodel.AuthanicationViewModel
import com.example.clinicapp.databinding.ActivityLoginBinding
import com.example.clinicapp.model.LoginResponseModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: AuthanicationViewModel
    private var mail: String = ""
    private var password: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(AuthanicationViewModel::class.java)

        setListener()
        setObserver()

    }

    private fun saveUserData(userData: LoginResponseModel) {
        val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("userType", userData.type)
        editor.putString("name", userData.name)
        editor.putString("email", userData.email)
        editor.apply()
    }

    private fun setObserver(){
        viewModel.loading.observe(this, Observer { registerResult ->
            registerResult ?: return@Observer
            if (registerResult) {
                binding.mainProgress.visibility = View.VISIBLE
                dimBackground()
            } else {
                binding.mainProgress.visibility = View.GONE
                clearDimBackground()
            }
        })
        viewModel.loginResult.observe(this, Observer { loginResult ->
            loginResult ?: return@Observer // handle null case if needed

            saveUserData(loginResult)
            when (loginResult.type) {
                "P" -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                "D" -> {
                    startActivity(Intent(this, BookingList::class.java))
                    finish()
                }
                "A" -> {
                startActivity(Intent(this, Admin::class.java))
                finish()
                }
            }
        })
    }
    private fun dimBackground() {
        val window = this.window
        val layoutParams = window.attributes
        layoutParams.alpha = 0.6f // يمكن تعديل هذه القيمة لتغيير مستوى التعتيم
        window.attributes = layoutParams
    }

    private fun clearDimBackground() {
        val window = this.window
        val layoutParams = window.attributes
        layoutParams.alpha = 1.0f // استعادة الشفافية الكاملة
        window.attributes = layoutParams
    }
    private fun setListener() {
        binding.gotoSign.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        binding.login.setOnClickListener {
            mail = binding.mail.text.toString()
            password = binding.password.text.toString()

            if (validate(mail, password)) {
                // Call login function in ViewModel
                viewModel.login(mail, password)
            }
        }
    }


    private fun validate(mail: String, password: String): Boolean {
        var isValid = true

        if (mail.isEmpty()) {
            binding.mail.error = "من فضلك ادخل الايميل"
            isValid = false
        }

        if (password.isEmpty()) {
            binding.password.error = "من فضلك ادخل الباسورد"
            isValid = false
        }

        return isValid
    }
}


