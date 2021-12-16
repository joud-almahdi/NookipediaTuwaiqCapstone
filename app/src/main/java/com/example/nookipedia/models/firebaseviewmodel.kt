package com.example.nookipedia.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nookipedia.repositories.firebaserepository

class firebaseviewmodel:ViewModel() {
    val firerepo:firebaserepository= firebaserepository.get()

    val firebaseerrordata=MutableLiveData<String>()


    fun deletefave()
    {
        TODO()
    }



    fun updatefave()
    {
        TODO()
    }



    fun addfave()
    {
        TODO()
    }



    fun newuser()
    {
        TODO()
    }

}