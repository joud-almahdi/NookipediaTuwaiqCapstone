package com.example.nookipedia.repositories

import android.annotation.SuppressLint
import android.content.Context
import com.example.nookipedia.apis.animalcrossingapi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class apirepository(val context: Context) {
    val retro= Retrofit.Builder().baseUrl("https://api.nookipedia.com").addConverterFactory(
        GsonConverterFactory.create()).build()
    val retrofitapi=retro.create(animalcrossingapi::class.java)
   suspend fun getfish()=retrofitapi.getfish()
   suspend fun getonefish()=retrofitapi.getonefish()
    suspend fun getonebug()=retrofitapi.getonebug()
   suspend fun getbug()=retrofitapi.getbug()
   suspend fun getsea()=retrofitapi.getsea()
    suspend fun getonesea()=retrofitapi.getonesea()



    companion object{
        private var instance:apirepository?=null

        fun init(context: Context)
        {
            if (instance==null)
            {
                instance= apirepository(context)
            }
        }

        fun get():apirepository
        {
            return instance?:throw Exception("Apiservicerepository must be initialized")
        }
    }

}