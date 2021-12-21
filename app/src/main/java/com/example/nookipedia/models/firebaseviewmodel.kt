package com.example.nookipedia.models

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nookipedia.data.favorites
import com.example.nookipedia.repositories.firebaserepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class firebaseviewmodel:ViewModel() {
    val firerepo:firebaserepository= firebaserepository.get()
    var favoritelivedata=MutableLiveData<favorites>()
    var firebaseerrordata=MutableLiveData<String>()


    fun deletefave(id:String)
    {
        viewModelScope.launch {
            try {
                val response=firerepo.deletefave(id)


            }
            catch (e:Exception)
            {
                firebaseerrordata.postValue(e.message)

            }
        }

    }



    fun updatefave()
    {
        TODO()
    }



    fun addfave(name:String)
    {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response=firerepo.addfave(name)

            }
            catch (e:Exception)
            {
                firebaseerrordata.postValue(e.message)
            }
        }

    }



    fun newuser()
    {
        TODO()
    }

}