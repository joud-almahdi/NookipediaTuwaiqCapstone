package com.example.nookipedia.models

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nookipedia.json.bugjson.bugjsonItem
import com.example.nookipedia.json.fishjason.fishjsonItem
import com.example.nookipedia.main.auth
import com.example.nookipedia.repositories.firebaserepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class addingbugsviewmodel:ViewModel() {
    val firerepo: firebaserepository = firebaserepository.get()
    var thefaves: bugjsonItem? =null
    var firebaseerrordata= MutableLiveData<String>()
    var livedatafortoasts= MutableLiveData<String>()



    fun addfave(id:String)
    {

        viewModelScope.launch(Dispatchers.IO) {
            try {


                if(thefaves!=null)
                {
                    val fave:MutableMap<String,Any> = hashMapOf(
                        "userid" to auth.currentUser!!.uid,
                        "crittername" to thefaves!!.name,
                        "imageurl" to thefaves!!.imageUrl,
                        "favnote" to "",
                        "favid" to thefaves!!.number.toString()
                    )
                    Log.d("inviewmodel",thefaves!!.name)
                    val response=firerepo.addfave(id,fave)
                    response.addOnSuccessListener {
                        livedatafortoasts.postValue("Success")

                    }

                }
                else
                {
                    firebaseerrordata.postValue("Error")
                }




            }
            catch (e: Exception)
            {
                firebaseerrordata.postValue(e.message)
            }
        }

    }

    fun checknewfavebeforeadd(name:String,id:String)
    {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response=firerepo.retrieveforadding(name)
                response.addOnSuccessListener {
                    if(it.count()>0)
                    {
                        livedatafortoasts.postValue("This critter already exists")
                    }
                    else
                    {
                        addfave(id)
                    }
                }
                    .addOnFailureListener {
                        firebaseerrordata.postValue(it.message)
                    }
            }
            catch (e: Exception)
            {
                firebaseerrordata.postValue(e.message)

            }
        }

    }










}