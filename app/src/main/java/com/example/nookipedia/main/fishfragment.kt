package com.example.nookipedia.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nookipedia.R
import com.example.nookipedia.adapterimport.fishadapter
import com.example.nookipedia.apis.animalcrossingapi
import com.example.nookipedia.databinding.FragmentFishfragmentBinding
import com.example.nookipedia.json.fishjason.fishjsonItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class fishfragment : Fragment() {
    var fisharray=ArrayList<fishjsonItem>()



    private lateinit var binding: FragmentFishfragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentFishfragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter=fishadapter(fisharray)
        binding.fishrecyclerview.adapter=adapter
        val retro= Retrofit.Builder().baseUrl("https://api.nookipedia.com").addConverterFactory(
            GsonConverterFactory.create()).build()
        val retrofitapi=retro.create(animalcrossingapi::class.java)
        retrofitapi.getfish().enqueue(object:Callback<List<fishjsonItem>>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<List<fishjsonItem>>,
                response: Response<List<fishjsonItem>>
            ) {
                response.body()?.run {
                    fisharray.addAll(this)
                    adapter.notifyDataSetChanged()
                }

            }

            override fun onFailure(call: Call<List<fishjsonItem>>, t: Throwable) {
                //mmm
            }

        })

    }


}