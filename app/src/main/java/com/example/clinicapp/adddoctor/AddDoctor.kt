package com.example.clinicapp.adddoctor

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.clinicapp.R
import com.example.clinicapp.admin.Admin
import com.example.clinicapp.authantication.viewmodel.SignupViewModel
import com.example.clinicapp.databinding.AddDoctorBinding
import com.example.clinicapp.databinding.DailogBinding
import com.example.clinicapp.model.RegisterModel

class AddDoctor : AppCompatActivity() {
    private lateinit var binding: AddDoctorBinding
    private lateinit var viewModel: SignupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddDoctorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(SignupViewModel::class.java)

        setListener()
        setObserver()

    }
    private fun setListener(){
        binding.back.setOnClickListener {
            startActivity(Intent(this, Admin::class.java))
            finish()
        }

        binding.add.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val name = binding.etName.text.toString()
            val bio = binding.etBio.text.toString()
            val phone = binding.etPhone.text.toString()
            val password = binding.etSPassword.text.toString()
            val department = binding.etDepartment.text.toString()
            val confirmPass = binding.etSConfPassword.text.toString()
            val price = binding.price.text.toString()

            if (validate(email, password, name, bio, phone,department)) {
                viewModel.register(
                    RegisterModel(
                    bio,
                    department,
                    email,
                    "0.0",
                    "0.0",
                    name,
                    password,
                    phone,
                    price,
                    "D"
                )
                )
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
    private fun validate(mail: String, password: String,firstName: String, bio: String,phone: String, department: String): Boolean {
        var isValid = true

        if (mail.isEmpty()) {
            binding.etEmail.error = "من فضلك ادخل الايميل"
            isValid = false
        }
        if (password.isEmpty()) {
            binding.etSPassword.error = "من فضلك ادخل الباسورد"
            isValid = false
        }
        if (firstName.isEmpty()) {
            binding.etName.error = "من فضلك ادخل الاسم"
            isValid = false
        }
        if (bio.isEmpty()) {
            binding.etBio.error = "من فضلك ادخل الاسم البايو "
            isValid = false
        }
        if (phone.isEmpty()) {
            binding.etPhone.error = "من فضلك ادخل رقم الهاتف"
            isValid = false
        }
        if (department.isEmpty()) {
            binding.etDepartment.error = "من فضلك ادخل القسم"
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
        binding.etName.setText("")
        binding.etEmail.setText("")
        binding.price.setText("")
        binding.etDepartment.setText("")
        binding.etSConfPassword.setText("")
        binding.etSPassword.setText("")
        binding.etBio.setText("")
        binding.etPhone.setText("")

    }
}