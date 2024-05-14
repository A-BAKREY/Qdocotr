package com.example.clinicapp.authantication

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.clinicapp.R
import com.example.clinicapp.authantication.viewmodel.SignupViewModel
import com.example.clinicapp.databinding.ActivitySignupBinding
import com.example.clinicapp.databinding.DailogBinding
import com.example.clinicapp.model.RegisterModel

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var viewModel: SignupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(SignupViewModel::class.java)

        setListener()
        setObserver()
    }

    private fun setListener(){
        binding.gotologin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.register.setOnClickListener {
            val email = binding.etMail.text.toString()
            val firstName = binding.etFirstname.text.toString()
            val lastName = binding.etLastname.text.toString()
            val phone = binding.phone.text.toString()
            val address = binding.address.text.toString()
            val password = binding.etSPassword.text.toString()
            val confirmPass = binding.etSConfPassword.text.toString()

            if (validate(email, password, firstName, lastName, phone, address)) {
                viewModel.register(RegisterModel(
                    address,
                    "",
                    email,
                    0.0,
                    0.0,
                    "$firstName $lastName",
                    password,
                    phone,
                    0,
                    "P"
                ))
            }
        }
    }

    private fun setObserver(){
        viewModel.register.observe(this, Observer { registerResult ->
            registerResult ?: return@Observer // handle null case if needed
            if (registerResult.code == 1 ){
                successDialog(R.drawable.circle,"تم الاضافه بنجاح")
                clear()
            }else if(registerResult.code == 0){
                successDialog(R.drawable.cancel,"حدث خطأ اثناء الاضافه")
            }
        })
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
    private fun validate(mail: String, password: String,firstName: String, lastName: String,phone: String, address: String): Boolean {
        var isValid = true

        if (mail.isEmpty()) {
            binding.etMail.error = "من فضلك ادخل الايميل"
            isValid = false
        }
        if (password.isEmpty()) {
            binding.etSPassword.error = "من فضلك ادخل الباسورد"
            isValid = false
        }
        if (firstName.isEmpty()) {
            binding.etFirstname.error = "من فضلك ادخل الاسم الاول"
            isValid = false
        }
        if (lastName.isEmpty()) {
            binding.etLastname.error = "من فضلك ادخل الاسم الاخير "
            isValid = false
        }
        if (phone.isEmpty()) {
            binding.phone.error = "من فضلك ادخل رقم الهاتف"
            isValid = false
        }
        if (address.isEmpty()) {
            binding.address.error = "من فضلك ادخل العنوان"
            isValid = false
        }

        return isValid
    }
    private fun successDialog(image: Int,text:String) {

        val dialog = Dialog(this)
        val binding = DailogBinding.inflate(LayoutInflater.from(this))
        dialog.setContentView(binding.root)
        dialog.setCancelable(true)
        val window: Window? = dialog.window
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        binding.dialogImage.setImageResource(image)
        binding.dialogText.text = text
        dialog.show()
    }
    private fun clear(){
        binding.phone.setText("")
        binding.etMail.setText("")
        binding.etLastname.setText("")
        binding.etFirstname.setText("")
        binding.etSConfPassword.setText("")
        binding.etSPassword.setText("")
        binding.address.setText("")


    }
}