package com.example.clinicapp.profile

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.clinicapp.authantication.LoginActivity
import com.example.clinicapp.databinding.ProfileActivityBinding

class ProfileFragment  : Fragment() {

    private var _binding: ProfileActivityBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ProfileActivityBinding.inflate(inflater, container, false)
        val view = binding.root

        val sharedPreferences =requireContext().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("name", "")
        val email = sharedPreferences.getString("email", "")

        binding.name.text = name
        binding.email.text = email

        binding.logout.setOnClickListener {
            logout()
        }
        return view
    }

    private fun logout() {
        val sharedPreferences = requireContext().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        AlertDialog.Builder(requireContext())
            .setTitle("Confirm Logout")
            .setMessage("Are you sure you want to sign out?")
            .setPositiveButton("yes") { dialog, _ ->
                editor.clear()
                editor.apply()
                startActivity(Intent(requireContext(), LoginActivity::class.java))
                requireActivity().finish()
                dialog.dismiss()
            }
            .setNegativeButton("no") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}