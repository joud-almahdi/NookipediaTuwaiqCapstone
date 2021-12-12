package com.example.nookipedia.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.nookipedia.R
import com.example.nookipedia.databinding.ActivityLoginactivityBinding
import com.example.nookipedia.databinding.ActivityRegisteractivityBinding
//import com.example.nookipedia.databinding.FragmentRegisterfragmentBinding
import com.google.firebase.auth.FirebaseAuth

private lateinit var binding: ActivityLoginactivityBinding
class loginactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.logintoregisterbutton.setOnClickListener {

            var intent=Intent(this,registeractivity::class.java)
            startActivity(intent)
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
                            var intent=Intent(this,MainActivity::class.java)
                            startActivity(intent)
                        }
                        else
                        {
                            Toast.makeText(this, it.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
            }

        }


    }
}