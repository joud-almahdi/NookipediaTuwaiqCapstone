package com.example.nookipedia.main

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.nookipedia.R
import com.example.nookipedia.databinding.ActivityRegisteractivityBinding
import com.example.nookipedia.repositories.authenticationviewmodel
import com.example.nookipedia.unittesting.registerunittesting
//import com.example.nookipedia.databinding.FragmentRegisterfragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

private lateinit var binding: ActivityRegisteractivityBinding
class registeractivity : AppCompatActivity() {

    var Channelid:String="456"
    var notificationid:Int=1

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


    }




    fun observers() {

        val userobserver=Observer<String>{
            sharededitor.putBoolean("status",true)
            sharededitor.putString("uid",user!!.uid)
            sharededitor.putString("email",user!!.email)
            sharededitor.commit()
        }

        binding.registerbutton.setOnClickListener {
            var enteredemail:String=binding.registeremailedittext.text.toString()
            var enteredpassword:String=binding.registerpasswordedittext.text.toString()
            if(validator.emailisvalid(enteredemail))
            {

                            Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show()

                            val intent=Intent(this,MainActivity::class.java)
                            createNotificationChannel("Registeration completed","Hello!, your user id is ${user!!.uid}",notificationid)
                            startActivity(intent)
                            finish()



            }
            else
            {
                Toast.makeText(this, "Email is invalid", Toast.LENGTH_SHORT).show()
            }


        }


    }





    //Create the channel needed for setting notifications
    private fun createNotificationChannel(name:String,descriptionText:String,id:Int) {
        val notificationBuilder =
            NotificationCompat.Builder(this, Channelid)
                .setSmallIcon(R.drawable.nook)
                .setContentTitle(name)
                .setContentText(descriptionText)
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