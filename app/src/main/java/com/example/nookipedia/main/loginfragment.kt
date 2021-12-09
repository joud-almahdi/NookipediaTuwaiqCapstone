package com.example.nookipedia.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.nookipedia.R
import com.example.nookipedia.databinding.FragmentLoginfragmentBinding
import com.google.firebase.auth.FirebaseAuth


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

            var enteredemail:String=binding.loginemailedittext.text.toString()
            var enteredpassword:String=binding.loginpasswordedittext.text.toString()
            if(enteredemail.isNotBlank() && enteredpassword.isNotBlank())
            {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(enteredemail,enteredpassword)
                    .addOnCompleteListener {
                        if(it.isSuccessful)
                        {
                            findNavController().navigate(R.id.action_loginfragment_to_fishfragment)
                        }
                        else
                        {
                            Toast.makeText(requireActivity(), it.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
            }

        }
    }


}