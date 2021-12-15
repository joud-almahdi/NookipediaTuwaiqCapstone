package com.example.nookipedia.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.nookipedia.R
import com.example.nookipedia.databinding.ActivityLoginactivityBinding
import com.example.nookipedia.databinding.ActivityRegisteractivityBinding
//import com.example.nookipedia.databinding.FragmentRegisterfragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class loginactivity : AppCompatActivity() {



    private lateinit var binding: ActivityLoginactivityBinding
    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shared=getSharedPreferences("Auth",Context.MODE_PRIVATE)
        sharededitor=shared.edit()
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
                            sharededitor.putBoolean("status",true)
                            sharededitor.putString("uid",user!!.uid)
                            sharededitor.putString("email",user!!.email)
                            sharededitor.commit()
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