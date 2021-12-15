package com.example.nookipedia.main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.motion.widget.MotionLayout
import com.example.nookipedia.R
import com.example.nookipedia.databinding.ActivitySplashBinding
import com.example.nookipedia.repositories.apirepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
val auth: FirebaseAuth = Firebase.auth
var user=auth.currentUser
lateinit var shared: SharedPreferences
lateinit var sharededitor: SharedPreferences.Editor
class Splash : AppCompatActivity() {


    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        shared=getSharedPreferences("Auth", Context.MODE_PRIVATE)
        sharededitor=shared.edit()


        apirepository.init(this)
        binding.motion.setTransitionListener(object:MotionLayout.TransitionListener{
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {

            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {

            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {

                if(shared.getBoolean("status",false)) {
                    val intent = Intent(this@Splash,MainActivity::class.java)
                    startActivity(intent)

                }
                else
                {
                    val intent2 = Intent(this@Splash,loginactivity::class.java)
                    startActivity(intent2)

                }

            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {

            }

        })


    }
}