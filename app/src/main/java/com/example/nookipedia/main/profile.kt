package com.example.nookipedia.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.nookipedia.R
import com.example.nookipedia.databinding.FragmentProfileBinding


class profile : Fragment() {

private lateinit var binding:FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentProfileBinding.inflate(layoutInflater, container, false)

        if(arguments!=null)
        {
            binding.useridinprofile.text=requireArguments().getString("id")
            binding.useremailinprofile.text=requireArguments().getString("email")
        }


        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logoutbutton.setOnClickListener {
           // findNavController().navigate(R.id.action_profile_to_loginfragment)
        }
    }


}