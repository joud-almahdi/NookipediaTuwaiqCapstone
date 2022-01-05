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

    private  val TAG = "firebaseviewmodel"




    fun deletefave(id:String)
    {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d(TAG,"In try")
                val response=firerepo.deletefave(id)
              response.addOnCompleteListener {
                  if(it.isSuccessful)
                  {
                      Log.d(TAG,"In If")
                      livedatafortoasts.postValue("Deleted")
                  }
                  else
                  {
                      Log.d(TAG,"In else")
                      firebaseerrordata.postValue(response.exception!!.message)
                  }
              }
            }
            catch (e:Exception)
            {
                Log.d(TAG,"In catch")
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






}