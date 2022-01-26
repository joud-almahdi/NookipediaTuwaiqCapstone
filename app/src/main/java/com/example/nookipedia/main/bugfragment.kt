package com.example.nookipedia.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.nookipedia.R
import com.example.nookipedia.adapterimport.bugadapter

import com.example.nookipedia.apis.animalcrossingapi
import com.example.nookipedia.databinding.FragmentBugfragmentBinding

import com.example.nookipedia.json.bugjson.bugjsonItem

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
    var searchbug= mutableListOf<bugjsonItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        shared=requireActivity().getSharedPreferences("Auth", Context.MODE_PRIVATE)
        sharededitor= shared.edit()
        setHasOptionsMenu(true)
        binding= FragmentBugfragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar.visibility=View.VISIBLE
        adapter= bugadapter(bugviewmodel,addingbugviewmodel)
        binding.bugrecyclerview.adapter=adapter
        observers()
        bugviewmodel.getbug()



    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        requireActivity().menuInflater.inflate(R.menu.uppermenu,menu)


        val searchitem=menu.findItem(R.id.app_bar_search)


        val searchView=searchitem.actionView as SearchView



        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.submitbug(

                    searchbug.filter { it.name.lowercase().contains(query!!.lowercase()) }
                )


                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                adapter.submitbug(

                    searchbug.filter { it.name.lowercase().contains(newText!!.lowercase()) }
                )
                return true
            }


        })

        searchitem.setOnActionExpandListener(object:MenuItem.OnActionExpandListener{
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {

                adapter.submitbug(searchbug)
                return true

            }

        })









    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId)
        {
            R.id.logout->{ sharededitor.putBoolean("status",false)
                sharededitor.commit()
                startActivity(Intent(requireActivity(),loginactivity::class.java))
                requireActivity().finish()
            }
            R.id.profile->findNavController().navigate(R.id.action_bugfragment_to_profile)
            R.id.favorite->findNavController().navigate(R.id.action_bugfragment_to_favoritefragment)
        }
        return super.onOptionsItemSelected(item)

    }
    fun observers()
    {
        bugviewmodel.buglivedata.observe(viewLifecycleOwner,{
            binding.bugrecyclerview.animate().alpha(0F)
            adapter.submitbug(it)
            binding.progressBar.visibility=View.INVISIBLE
            searchbug=it as MutableList<bugjsonItem>
            binding.bugrecyclerview.animate().alpha(1F)
        })


        bugviewmodel.errorlivedata.observe(viewLifecycleOwner,{
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        })
    }


}