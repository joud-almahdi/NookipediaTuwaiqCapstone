package com.example.nookipedia.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import com.example.nookipedia.R
import com.example.nookipedia.adapterimport.bugadapter
import com.example.nookipedia.adapterimport.fishadapter
import com.example.nookipedia.apis.animalcrossingapi
import com.example.nookipedia.databinding.FragmentBugfragmentBinding
import com.example.nookipedia.databinding.FragmentFishfragmentBinding
import com.example.nookipedia.json.bugjson.bugjsonItem
import com.example.nookipedia.json.fishjason.fishjsonItem
import com.example.nookipedia.models.animalcrossingviewmodel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class bugfragment : Fragment() {

    var bugarray=ArrayList<bugjsonItem>()
    private val bugviewmodel: animalcrossingviewmodel by activityViewModels()
    private lateinit var binding: FragmentBugfragmentBinding
    private lateinit var adapter :bugadapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentBugfragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= bugadapter(bugviewmodel)
        binding.bugrecyclerview.adapter=adapter
        observers()
        bugviewmodel.getbug()

    }



    fun observers()
    {
        bugviewmodel.buglivedata.observe(viewLifecycleOwner,{

            adapter.submitbug(it)
        })


        bugviewmodel.errorlivedata.observe(viewLifecycleOwner,{
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        })
    }


}