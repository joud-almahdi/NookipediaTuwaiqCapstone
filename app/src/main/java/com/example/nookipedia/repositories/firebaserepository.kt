package com.example.nookipedia.repositories
import android.annotation.SuppressLint
import android.content.Context
import com.example.nookipedia.adapter.favoriteadaptersimport.favoritefishadapter
import com.example.nookipedia.databinding.FragmentFavoritefragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class firebaserepository(val context:Context) {

    val auth: FirebaseAuth = Firebase.auth
    val db=FirebaseFirestore.getInstance()


    fun updatefave(id:String,note:String)=db.collection("favorites").document(id).update("favnote",note)


    fun deletefave(id:String)=db.collection("favorites").document(id).delete()

        fun retreivefaves()= db.collection("favorites").whereEqualTo("userid",Firebase.auth.currentUser!!.uid)

    fun addfave( name:String)= db.collection("favorites").whereEqualTo("crittername",name).whereEqualTo("userid",auth.currentUser!!.uid).get()
    fun registeruser(enteredemail:String,enteredpassword:String)=FirebaseAuth.getInstance().createUserWithEmailAndPassword(enteredemail,enteredpassword)



    companion object
    {
        @SuppressLint("StaticFieldLeak")
        private var instance:firebaserepository?=null
        fun init(context:Context)
        {
            if(instance==null)
            {
                instance= firebaserepository(context)
            }
        }

        fun get():firebaserepository
        {
            return instance?: throw Exception("Should be initialized")
        }
    }


}