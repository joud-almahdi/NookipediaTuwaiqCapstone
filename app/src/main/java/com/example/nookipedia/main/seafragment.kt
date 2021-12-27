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
import com.example.nookipedia.adapterimport.fishadapter
import com.example.nookipedia.adapterimport.seaadapter
import com.example.nookipedia.apis.animalcrossingapi
import com.example.nookipedia.databinding.FragmentFishfragmentBinding
import com.example.nookipedia.databinding.FragmentSeafragmentBinding
import com.example.nookipedia.json.seajson.seajsonItem
import com.example.nookipedia.models.addingseaviewmodel
import com.example.nookipedia.models.animalcrossingviewmodel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class seafragment : Fragment() {

    var seaarray=ArrayList<seajsonItem>()
    private lateinit var binding: FragmentSeafragmentBinding
    private lateinit var adapter:seaadapter
    private val seaviewmodel:animalcrossingviewmodel by activityViewModels()
    private val seadetailviewmodel:addingseaviewmodel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSeafragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         adapter= seaadapter(seaviewmodel,seadetailviewmodel)
        binding.searecyclerview.adapter=adapter
            observer()
        seaviewmodel.getsea()
    }


    fun observer()
    {
        seaviewmodel.sealivedata.observe(viewLifecycleOwner,{
            adapter.submitsea(it)
        })

        seaviewmodel.errorlivedata.observe(viewLifecycleOwner,{
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        })
    }


}