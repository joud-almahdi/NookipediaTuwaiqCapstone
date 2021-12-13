package com.example.nookipedia.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.nookipedia.R
import com.example.nookipedia.databinding.FragmentDetailfragmentBinding
import com.example.nookipedia.models.animalcrossingviewmodel
import com.squareup.picasso.Picasso


class detailfragment : Fragment() {
    private lateinit var binding: FragmentDetailfragmentBinding
    private val detailviewmodel:animalcrossingviewmodel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentDetailfragmentBinding.inflate(layoutInflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observers()

    }


    fun observers()
    {
        detailviewmodel.onefishlivedata.observe(viewLifecycleOwner,{
            binding.catchingphraseindetailview.text=it.catchphrases[0]
            Picasso.get().load(it.renderUrl).into(binding.itemimageindetailview)
        })
    }


}