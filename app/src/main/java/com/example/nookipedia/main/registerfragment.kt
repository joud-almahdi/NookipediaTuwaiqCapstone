package com.example.nookipedia.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.nookipedia.R
import com.example.nookipedia.databinding.FragmentLoginfragmentBinding
import com.example.nookipedia.databinding.FragmentRegisterfragmentBinding


class registerfragment : Fragment() {


    private lateinit var binding: FragmentRegisterfragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentRegisterfragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registertologinbutton.setOnClickListener {
            findNavController().navigate(R.id.action_registerfragment_to_loginfragment)
        }

        binding.registerbutton.setOnClickListener {
            findNavController().navigate(R.id.action_registerfragment_to_fishfragment)
        }
    }

}