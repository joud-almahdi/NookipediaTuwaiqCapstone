package com.example.nookipedia.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.akexorcist.localizationactivity.core.LocalizationActivityDelegate
import com.akexorcist.localizationactivity.core.OnLocaleChangedListener
import com.example.nookipedia.R
import com.example.nookipedia.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class profile : Fragment() {

private lateinit var binding:FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root

    }


    @SuppressLint("CommitPrefEdits")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val localizationDelegate = LocalizationActivityDelegate(requireActivity())
        shared=requireActivity().getSharedPreferences("Auth", Context.MODE_PRIVATE)
        sharededitor=shared.edit()
        binding.useridinprofile.text=auth.currentUser!!.uid
        binding.useremailinprofile.text=auth.currentUser!!.email
        binding.logoutbutton.setOnClickListener {
            sharededitor.putBoolean("status",false)
            sharededitor.commit()
            startActivity(Intent(requireActivity(),loginactivity::class.java))

        }


        binding.arradioinprofile.setOnClickListener {
            localizationDelegate.setLanguage(requireActivity(),"ar")
        }


        binding.enradioinprofile.setOnClickListener {
            localizationDelegate.setLanguage(requireActivity(),"en_US")
        }
    }




}