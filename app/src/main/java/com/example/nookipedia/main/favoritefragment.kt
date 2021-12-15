package com.example.nookipedia.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.nookipedia.R
import com.example.nookipedia.adapter.favoriteadaptersimport.favoritefishadapter
import com.example.nookipedia.data.favorites
import com.example.nookipedia.databinding.FragmentFavoritefragmentBinding
import com.example.nookipedia.json.fishjason.fishjsonItem
import com.example.nookipedia.models.animalcrossingviewmodel
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class favoritefragment : Fragment() {


    private lateinit var binding:FragmentFavoritefragmentBinding
    private lateinit var adapter:favoritefishadapter
    private lateinit var db:FirebaseFirestore
    private var favefish=mutableListOf<favorites>()
//https://www.youtube.com/watch?v=Ly0xwWlUpVM
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

      binding=FragmentFavoritefragmentBinding.inflate(layoutInflater, container, false)
    adapter= favoritefishadapter()
    adapter.submittedlist(favefish)
    binding.favoriterecyclerview.adapter=adapter
    return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        EventListener()


    }


    @SuppressLint("NotifyDataSetChanged")
    fun EventListener()
    {
        db= FirebaseFirestore.getInstance()
        db.collection("favorites").addSnapshotListener(object:EventListener<QuerySnapshot>
        {
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {

                if(error!=null)
                {
                    Toast.makeText(requireActivity(), error.message, Toast.LENGTH_SHORT).show()
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
                adapter.notifyDataSetChanged()
            }

        })



    }


    fun delete()
    {
        val fave= Firebase.firestore.collection("favorites")
        val favetobedeleted=fave.whereEqualTo("crittername","Dungeness Crab")
        try {
            fave.document().delete()

        }
        catch (e:Exception)
        {
            Toast.makeText(requireActivity(), "error", Toast.LENGTH_SHORT).show()
        }
    }




}