package com.example.nookipedia.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.nookipedia.R
import com.example.nookipedia.adapterimport.bugadapter
import com.example.nookipedia.adapterimport.fishadapter
import com.example.nookipedia.apis.animalcrossingapi
import com.example.nookipedia.databinding.FragmentBugfragmentBinding
import com.example.nookipedia.databinding.FragmentFishfragmentBinding
import com.example.nookipedia.json.bugjson.bugjsonItem
import com.example.nookipedia.json.fishjason.fishjsonItem
import com.example.nookipedia.models.addingbugsviewmodel
import com.example.nookipedia.models.animalcrossingviewmodel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class bugfragment : Fragment() {

    var bugarray=ArrayList<bugjsonItem>()
    private val bugviewmodel: animalcrossingviewmodel by activityViewModels()
    private val addingbugviewmodel: addingbugsviewmodel by activityViewModels()
    private lateinit var binding: FragmentBugfragmentBinding
    private lateinit var adapter :bugadapter
    lateinit var sharededitor: SharedPreferences.Editor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        shared=requireActivity().getSharedPreferences("Auth", Context.MODE_PRIVATE)
        sharededitor= shared.edit()
        binding= FragmentBugfragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= bugadapter(bugviewmodel,addingbugviewmodel)
        binding.bugrecyclerview.adapter=adapter
        observers()
        bugviewmodel.getbug()



    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        requireActivity().menuInflater.inflate(R.menu.uppermenu,menu)
        val search=menu.findItem(R.id.app_bar_search)
        val logout=menu.findItem(R.id.logout)
        val favorite=menu.findItem(R.id.favorite)
        val profile=menu.findItem(R.id.profile)








    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId)
        {
            R.id.logout->{ sharededitor.putBoolean("status",false)
                sharededitor.commit()
                startActivity(Intent(requireActivity(),loginactivity::class.java))
                requireActivity().finish()
            }
            R.id.profile->findNavController().navigate(R.id.action_fishfragment_to_profile)
            R.id.favorite->findNavController().navigate(R.id.action_fishfragment_to_favoritefragment)
        }
        return super.onOptionsItemSelected(item)

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