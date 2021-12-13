package com.example.nookipedia.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.nookipedia.R
import com.example.nookipedia.adapterimport.seaadapter
import com.example.nookipedia.databinding.FragmentSeadetailBinding
import com.example.nookipedia.models.animalcrossingviewmodel
import com.squareup.picasso.Picasso


class seadetailFragment : Fragment() {

    private lateinit var binding:FragmentSeadetailBinding
    private val seaviewmodel:animalcrossingviewmodel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding=FragmentSeadetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observers()

    }

    fun observers()
    {
        seaviewmodel.onesealivedata.observe(viewLifecycleOwner,{
            binding.catchingphraseinseadetailview.text=it.catchphrases[0]
            Picasso.get().load(it.renderUrl).into(binding.itemimageinseadetailview)

        })
    }





}