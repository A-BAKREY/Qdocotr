package com.example.clinicapp.intro.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.clinicapp.intro.fragment.IntroOne
import com.example.clinicapp.intro.fragment.IntroThree
import com.example.clinicapp.intro.fragment.IntroTwo

class IntroApater(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 3 // Number of onboarding screens
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> IntroOne()
            1 -> IntroTwo()
            2 -> IntroThree()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}