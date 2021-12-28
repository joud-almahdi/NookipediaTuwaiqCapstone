package com.example.nookipedia.repositories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class authenticationviewmodel: ViewModel() {

    val firerepo:firebaserepository= firebaserepository.get()
    var firebaseerrordata= MutableLiveData<String>()
    var livedatafortoasts= MutableLiveData<String>()





    fun register(enteredemail:String,enteredpassword:String)
    {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response=firerepo.registeruser(enteredemail,enteredpassword)


                response.addOnCompleteListener {
                    if (it.isSuccessful)
                    {
                        livedatafortoasts.postValue("Welcome!")
                    }
                    else
                    {
                        firebaseerrordata.postValue("Error")
                    }
                }

            }
            catch (e:Exception)
            {
                firebaseerrordata.postValue(e.message)
            }

        }

    }
}