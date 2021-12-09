package com.example.nookipedia.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.nookipedia.R
import com.example.nookipedia.databinding.FragmentLoginfragmentBinding
import com.example.nookipedia.databinding.FragmentRegisterfragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


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
            var enteredemail:String=binding.registeremailedittext.text.toString()
            var enteredpassword:String=binding.registerpasswordedittext.text.toString()
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(enteredemail,enteredpassword)
                .addOnCompleteListener {
                    if(it.isSuccessful)
                    {
                        val firebaseuser:FirebaseUser=it.result!!.user!!
                        Toast.makeText(requireActivity(), "Welcome", Toast.LENGTH_SHORT).show()
                        var bundle= bundleOf("id" to firebaseuser.uid,"email" to firebaseuser.email)
                        findNavController().navigate(R.id.action_registerfragment_to_fishfragment,bundle)


                    }
                    else
                        Toast.makeText(requireActivity(), it.exception!!.message.toString(), Toast.LENGTH_SHORT).show()


            }


        }
    }

}