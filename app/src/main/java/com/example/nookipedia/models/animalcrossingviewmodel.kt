package com.example.nookipedia.models

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nookipedia.json.bugjson.bugjsonItem
import com.example.nookipedia.json.fishjason.fishjsonItem
import com.example.nookipedia.json.seajson.seajsonItem
import com.example.nookipedia.repositories.apirepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class animalcrossingviewmodel:ViewModel() {
    val apirepository:apirepository= com.example.nookipedia.repositories.apirepository.get()
    val fishlivedata=MutableLiveData <List<fishjsonItem>>()
    val buglivedata=MutableLiveData<List<bugjsonItem>>()
    val sealivedata=MutableLiveData<List<seajsonItem>>()
    val errorlivedata=MutableLiveData<String>()
    val onefishlivedata=MutableLiveData<fishjsonItem>()
    val onebuglivedata=MutableLiveData<bugjsonItem>()
    val onesealivedata=MutableLiveData<seajsonItem>()


    fun getfish()
    {
    viewModelScope.launch(Dispatchers.IO) {
        try {

            val response=apirepository.getfish()
            if(response.isSuccessful)
            {
                response.body()?.run {
                fishlivedata.postValue(this)
                }
            }
            else
            {
                errorlivedata.postValue(response.message())
            }
        }
        catch (e:Exception)
        {
           errorlivedata.postValue(e.message.toString())
        }

    }

    }






    fun getbug()
    {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response=apirepository.getbug()
                if(response.isSuccessful)
                {
                    response.body()?.run {
                        buglivedata.postValue(this)
                    }
                }
                else
                {
                    errorlivedata.postValue(response.message())
                }
            }
            catch (e:Exception)
            {
                errorlivedata.postValue(e.message.toString())
            }
        }

    }



    fun getsea()
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            try {
                val response=apirepository.getsea()
                if(response.isSuccessful)
                {
                    response.body()?.run {
                        sealivedata.postValue(this)
                    }
                }
                else
                {
                    errorlivedata.postValue(response.message())
                }
            }
            catch (e:Exception)
            {
                errorlivedata.postValue(e.message.toString())
            }
        }
    }







}