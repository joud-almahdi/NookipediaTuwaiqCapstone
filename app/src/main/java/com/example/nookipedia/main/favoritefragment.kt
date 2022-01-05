package com.example.nookipedia.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.nookipedia.R
import com.example.nookipedia.adapter.favoriteadaptersimport.favoritefishadapter
import com.example.nookipedia.data.favorites
import com.example.nookipedia.databinding.FragmentFavoritefragmentBinding
import com.example.nookipedia.json.fishjason.fishjsonItem
import com.example.nookipedia.models.animalcrossingviewmodel
import com.example.nookipedia.models.firebaseviewmodel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class favoritefragment : Fragment() {


    private lateinit var binding:FragmentFavoritefragmentBinding
    private lateinit var adapter:favoritefishadapter
    private val favoriteviewmodel:firebaseviewmodel by activityViewModels()
    private var favefish=mutableListOf<favorites>()
    var searchfave= mutableListOf<favorites>()
    //https://www.youtube.com/watch?v=Ly0xwWlUpVM
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentFavoritefragmentBinding.inflate(layoutInflater, container, false)
        adapter= favoritefishadapter(requireActivity(),favoriteviewmodel)
        adapter.submittedlist(favefish)
        binding.favoriterecyclerview.adapter=adapter
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
        favoriteviewmodel.getfave()




    }



    fun observer()
    {
        favoriteviewmodel.favoritelivedata.observe(viewLifecycleOwner,{


            if(it.isEmpty())
            {
                binding.favoriterecyclerview.visibility=INVISIBLE
                binding.empty.visibility= VISIBLE
            }
            else
            {
                binding.favoriterecyclerview.visibility= VISIBLE
                binding.empty.visibility= INVISIBLE
                favoriteviewmodel.getfave()
                adapter.submittedlist(it)
            }


        })


        favoriteviewmodel.firebaseerrordata.observe(viewLifecycleOwner,{
            it?.let {
                Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
                favoriteviewmodel.firebaseerrordata.postValue(null)
            }
        })

        favoriteviewmodel.livedatafortoasts.observe(viewLifecycleOwner,{
            it?.let {
                Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
                favoriteviewmodel.livedatafortoasts.postValue(null)
            }

        })
    }




}