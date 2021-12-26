package com.example.nookipedia.models

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nookipedia.data.favorites
import com.example.nookipedia.json.fishjason.fishjsonItem
import com.example.nookipedia.main.auth
import com.example.nookipedia.repositories.firebaserepository
import com.google.firebase.firestore.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class firebaseviewmodel:ViewModel() {
    val firerepo:firebaserepository= firebaserepository.get()
    var favoritelivedata= MutableLiveData<List<favorites>>()
    var firebaseerrordata=MutableLiveData<String>()
    var livedatafortoasts=MutableLiveData<String>()
    val thefaves: fishjsonItem? =null





    fun deletefave(id:String)
    {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response=firerepo.deletefave(id)
              response.addOnCompleteListener {
                  if(it.isSuccessful)
                  {
                      livedatafortoasts.postValue("Deleted")
                  }
                  else
                  {
                      firebaseerrordata.postValue(response.exception!!.message)
                  }
              }
            }
            catch (e:Exception)
            {
                firebaseerrordata.postValue(e.message)

            }
        }

    }



    fun updatefave(id:String,note:String)
    {
        viewModelScope.launch {
            try {

                val response=firerepo.updatefave(id,note)
                response.addOnSuccessListener {
                   livedatafortoasts.postValue("Updated successfully")
                }
                    .addOnFailureListener {
                        firebaseerrordata.postValue(it.message)
                    }
            }
            catch (e:Exception)
            {
                firebaseerrordata.postValue(e.message)
            }

        }
    }



    fun addfave(id:String)
    {

        viewModelScope.launch(Dispatchers.IO) {
            try {


                if(thefaves!=null)
                {
                    val fave:MutableMap<String,Any> = hashMapOf(
                        "userid" to auth.currentUser!!.uid,
                        "crittername" to thefaves.name,
                        "imageurl" to thefaves.imageUrl,
                        "favnote" to "",
                        "favid" to thefaves.number.toString()
                    )
                    val id=thefaves.number.toString()
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
            catch (e:Exception)
            {
                firebaseerrordata.postValue(e.message)
            }
        }

    }


    fun getfave()
    {
        var favefish=mutableListOf<favorites>()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response=firerepo.retreivefaves()
                response.addSnapshotListener(object:EventListener<QuerySnapshot>{
                    override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                        if(error!=null)
                        {
                            livedatafortoasts.postValue(error.message)
                            return
                        }
                        for(dc:DocumentChange in value?.documentChanges!!)
                        {
                            if(dc.type==DocumentChange.Type.ADDED)
                            {

                                favefish.add(dc.document.toObject(favorites::class.java))
                                Log.d("زوع",favefish.toString())


                            }
                        }
                        favoritelivedata.postValue(favefish)

                    }


                })

            }
            catch (e:Exception)
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
            catch (e:Exception)
            {
                firebaseerrordata.postValue(e.message)

            }            }

        }


}