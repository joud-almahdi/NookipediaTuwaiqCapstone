package com.example.nookipedia.main

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.akexorcist.localizationactivity.core.LocalizationActivityDelegate
import com.akexorcist.localizationactivity.core.OnLocaleChangedListener
import com.example.nookipedia.R
import com.example.nookipedia.databinding.ActivityRegisteractivityBinding
import com.example.nookipedia.unittesting.registerunittesting
//import com.example.nookipedia.databinding.FragmentRegisterfragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.util.*

private lateinit var binding: ActivityRegisteractivityBinding
class registeractivity : AppCompatActivity(),OnLocaleChangedListener {
    private val localizationDelegate = LocalizationActivityDelegate(this)

    var Channelid:String="456"
    var notificationid:Int=1

    private val validator=registerunittesting()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        localizationDelegate.addOnLocaleChangedListener(this)
        localizationDelegate.onCreate()
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
                            createNotificationChannel("Registeration completed","Hello!, your user id is ${user!!.uid}",notificationid)
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

        binding.arbuttoninregister.setOnClickListener {

            localizationDelegate.setLanguage(this,"ar")

        }

        binding.enbuttoninregister.setOnClickListener {
            localizationDelegate.setLanguage(this,"en_US")
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


    public override fun onResume() {
        super.onResume()
        localizationDelegate.onResume(this)
    }

    override fun attachBaseContext(newBase: Context) {
        applyOverrideConfiguration(localizationDelegate.updateConfigurationLocale(newBase))
        super.attachBaseContext(newBase)
    }

    override fun getApplicationContext(): Context {
        return localizationDelegate.getApplicationContext(super.getApplicationContext())
    }

    override fun getResources(): Resources {
        return localizationDelegate.getResources(super.getResources())
    }

    fun setLanguage(language: String?) {
        localizationDelegate.setLanguage(this, language!!)
    }

    fun setLanguage(locale: Locale?) {
        localizationDelegate.setLanguage(this, locale!!)
    }

    val currentLanguage: Locale
        get() = localizationDelegate.getLanguage(this)






    override fun onAfterLocaleChanged() {
        //
    }

    override fun onBeforeLocaleChanged() {
        //
    }


}