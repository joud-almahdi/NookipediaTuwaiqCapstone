package com.example.nookipedia.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.nookipedia.R
import com.example.nookipedia.adapterimport.fishadapter
import com.example.nookipedia.apis.animalcrossingapi
import com.example.nookipedia.databinding.ActivityMainBinding
import com.example.nookipedia.databinding.FragmentFishfragmentBinding
import com.example.nookipedia.json.fishjason.fishjsonItem
import com.example.nookipedia.models.addingspeciesviewmodel
import com.example.nookipedia.models.animalcrossingviewmodel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class fishfragment : Fragment() {
    private val fishviewmodel:animalcrossingviewmodel by activityViewModels()
    private val forfishdetail:addingspeciesviewmodel by activityViewModels()
    private lateinit var binding: FragmentFishfragmentBinding
    private lateinit var adapter:fishadapter
    lateinit var sharededitor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    @SuppressLint("CommitPrefEdits")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        shared=requireActivity().getSharedPreferences("Auth",MODE_PRIVATE)
        sharededitor= shared.edit()
        //Toast.makeText(requireActivity(), shared.getString("uid","0"), Toast.LENGTH_SHORT).show()
        binding= FragmentFishfragmentBinding.inflate(layoutInflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         adapter=fishadapter(fishviewmodel,forfishdetail)
        binding.fishrecyclerview.adapter=adapter
        observe()
        fishviewmodel.getfish()


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


    fun observe()
    {
        fishviewmodel.fishlivedata.observe(viewLifecycleOwner,{

            adapter.submitfish(it)

        })


        fishviewmodel.errorlivedata.observe(viewLifecycleOwner,{

            it?.let {
                Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
            }

        })

    }


}