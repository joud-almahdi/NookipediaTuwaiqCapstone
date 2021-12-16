package com.example.nookipedia.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.nookipedia.R
import com.example.nookipedia.databinding.ActivityRegisteractivityBinding
import com.example.nookipedia.unittesting.registerunittesting
//import com.example.nookipedia.databinding.FragmentRegisterfragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

private lateinit var binding: ActivityRegisteractivityBinding
class registeractivity : AppCompatActivity() {

    private val validator=registerunittesting()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shared=getSharedPreferences("Auth", Context.MODE_PRIVATE)
        sharededitor=shared.edit()
        binding= ActivityRegisteractivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registertologinbutton.setOnClickListener {
            val intent=Intent(this,loginactivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.registerbutton.setOnClickListener {
            var enteredemail:String=binding.registeremailedittext.text.toString()
            var enteredpassword:String=binding.registerpasswordedittext.text.toString()
            if(validator.emailisvalid(enteredemail))
            {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(enteredemail,enteredpassword)
                    .addOnCompleteListener {
                        if(it.isSuccessful)
                        {


                            sharededitor.putBoolean("status",true)
                            sharededitor.putString("uid",user!!.uid)
                            sharededitor.putString("email",user!!.email)
                            sharededitor.commit()
                            Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show()

                            val intent=Intent(this,MainActivity::class.java)
                            startActivity(intent)
                            finish()


                        }
                        else
                            Toast.makeText(this, it.exception!!.message.toString(), Toast.LENGTH_SHORT).show()


                    }
            }
            else
            {
                Toast.makeText(this, "Email is invalid", Toast.LENGTH_SHORT).show()
            }


        }
    }
}