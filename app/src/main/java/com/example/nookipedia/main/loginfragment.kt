package com.example.nookipedia.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.nookipedia.R
import com.example.nookipedia.databinding.FragmentLoginfragmentBinding


class loginfragment : Fragment() {

private lateinit var binding:FragmentLoginfragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding=FragmentLoginfragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.logintoregisterbutton.setOnClickListener {
        findNavController().navigate(R.id.action_loginfragment_to_registerfragment)
        }


        binding.loginbutton.setOnClickListener {
            findNavController().navigate(R.id.action_loginfragment_to_fishfragment)
        }
    }


}