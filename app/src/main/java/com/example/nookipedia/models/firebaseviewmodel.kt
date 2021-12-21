package com.example.nookipedia.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nookipedia.data.favorites
import com.example.nookipedia.repositories.firebaserepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class firebaseviewmodel:ViewModel() {
    val firerepo:firebaserepository= firebaserepository.get()
    val favoritelivedata=MutableLiveData<favorites>()
    val firebaseerrordata=MutableLiveData<String>()


    fun deletefave( name:String)
       {
        viewModelScope.launch {
            firerepo.deletefave(name)
        }

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