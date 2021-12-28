package com.example.nookipedia.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.view.View.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.nookipedia.R
import com.example.nookipedia.adapter.favoriteadaptersimport.favoritefishadapter
import com.example.nookipedia.data.favorites
import com.example.nookipedia.databinding.FragmentFavoritefragmentBinding
import com.example.nookipedia.json.fishjason.fishjsonItem
import com.example.nookipedia.json.seajson.seajsonItem
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
    private lateinit var db:FirebaseFirestore
    private val favoriteviewmodel:firebaseviewmodel by activityViewModels()
    private var favefish=mutableListOf<favorites>()
    var searchfave= mutableListOf<favorites>()
//https://www.youtube.com/watch?v=Ly0xwWlUpVM
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
    setHasOptionsMenu(true)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        requireActivity().menuInflater.inflate(R.menu.uppermenuforfave,menu)

        val searchitem=menu.findItem(R.id.app_bar_search)


        val searchView=searchitem.actionView as SearchView

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.submittedlist(

                    searchfave.filter { it.crittername!!.lowercase().contains(query!!.lowercase()) }
                )


                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                adapter.submittedlist(

                    searchfave.filter { it.crittername!!.lowercase().contains(newText!!.lowercase()) }
                )
                return true
            }


        })

        searchitem.setOnActionExpandListener(object: MenuItem.OnActionExpandListener{
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {

                adapter.submittedlist(searchfave)
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
            R.id.profile->findNavController().navigate(R.id.action_favoritefragment_to_profile)

        }
        return super.onOptionsItemSelected(item)

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
                    binding.favoriterecyclerview.animate().alpha(0F)
                    adapter.submittedlist(it)
                    searchfave=it as MutableList<favorites>
                    binding.favoriterecyclerview.animate().alpha(1F)

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