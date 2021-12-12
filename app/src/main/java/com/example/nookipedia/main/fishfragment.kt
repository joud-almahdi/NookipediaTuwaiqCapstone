package com.example.nookipedia.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.nookipedia.R
import com.example.nookipedia.adapterimport.fishadapter
import com.example.nookipedia.apis.animalcrossingapi
import com.example.nookipedia.databinding.ActivityMainBinding
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
    private lateinit var navcontroller: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val search=menu.findItem(R.id.app_bar_search)
        val logout=menu.findItem(R.id.logout)
        val favorite=menu.findItem(R.id.favorite)
        val profile=menu.findItem(R.id.profile)





    }


}