package com.example.nookipedia.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.nookipedia.R
import com.example.nookipedia.adapterimport.fishadapter
import com.example.nookipedia.adapterimport.seaadapter
import com.example.nookipedia.apis.animalcrossingapi
import com.example.nookipedia.databinding.FragmentFishfragmentBinding
import com.example.nookipedia.databinding.FragmentSeafragmentBinding
import com.example.nookipedia.json.bugjson.bugjsonItem
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
    var searchsea= mutableListOf<seajsonItem>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        binding= FragmentSeafragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar3.visibility=View.VISIBLE
         adapter= seaadapter(seaviewmodel,seadetailviewmodel)
        binding.searecyclerview.adapter=adapter
            observer()
        seaviewmodel.getsea()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        requireActivity().menuInflater.inflate(R.menu.uppermenu,menu)


        val searchitem=menu.findItem(R.id.app_bar_search)


        val searchView=searchitem.actionView as SearchView



        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.submitsea(

                    searchsea.filter { it.name.lowercase().contains(query!!.lowercase()) }
                )


                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                adapter.submitsea(

                    searchsea.filter { it.name.lowercase().contains(newText!!.lowercase()) }
                )
                return true
            }


        })

        searchitem.setOnActionExpandListener(object: MenuItem.OnActionExpandListener{
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {

                adapter.submitsea(searchsea)
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
            R.id.profile->findNavController().navigate(R.id.action_seafragment_to_profile)
            R.id.favorite->findNavController().navigate(R.id.action_seafragment_to_favoritefragment)
        }
        return super.onOptionsItemSelected(item)

    }




    fun observer()
    {
        seaviewmodel.sealivedata.observe(viewLifecycleOwner,{
            binding.searecyclerview.animate().alpha(0F)
            adapter.submitsea(it)
            binding.progressBar3.visibility=View.INVISIBLE
            searchsea=it as MutableList<seajsonItem>
            binding.searecyclerview.animate().alpha(1F)
        })

        seaviewmodel.errorlivedata.observe(viewLifecycleOwner,{
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        })
    }


}