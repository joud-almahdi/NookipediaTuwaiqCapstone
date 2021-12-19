package com.example.nookipedia.main

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
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

    var Channelid:String="456"
    var notificationid:Int=1

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
                            createNotificationChannel("Login Successful","Hello!",notificationid)
                            startActivity(intent)
                            finish()
                        }
                        else
                        {
                            Toast.makeText(this, it.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
            }

        }


    }





    //Create the channel needed for setting notifications
    private fun createNotificationChannel(name:String,descriptionText:String,id:Int) {
        val notificationBuilder =
            NotificationCompat.Builder(this, Channelid)
                .setSmallIcon(R.drawable.nook)
                .setContentTitle("Login Successful")
                .setContentText("Welcome!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "SSSS"
            var descriptionText = "A text"
            var importance = NotificationManager.IMPORTANCE_DEFAULT
            var channel = NotificationChannel(Channelid, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
               getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
            with(NotificationManagerCompat.from(this)) {
                // notificationId is a unique int for each notification that you must define
                notify(notificationid, notificationBuilder.build())
            }


        }

    }



















}