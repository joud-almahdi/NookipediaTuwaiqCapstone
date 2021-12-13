package com.example.nookipedia.main

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.nookipedia.R
import com.example.nookipedia.databinding.FragmentBugdetailfragmentBinding
import com.example.nookipedia.models.animalcrossingviewmodel
import com.squareup.picasso.Picasso


class bugdetailfragment : Fragment() {
private lateinit var binding:FragmentBugdetailfragmentBinding
private val bugviewmodel:animalcrossingviewmodel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentBugdetailfragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observers()
        val sound: MediaPlayer = MediaPlayer.create(requireActivity(),R.raw.obtain)
        sound.start()


    }


    @SuppressLint("SetTextI18n")
    fun observers()
    {
        bugviewmodel.onebuglivedata.observe(viewLifecycleOwner,{
            binding.catchingphraseinbugdetailview.text=it.catchphrases[0]
            Picasso.get().load(it.renderUrl).into(binding.itemimageinbugdetailview)
            binding.locationinbugdetailview.text="Location:........${it.location}"
            binding.nookpriceinbugdetailview.text="Price at Nook's cranny:........${it.sellNook} Bells"
            binding.nookpriceinbugdetailview.text="Flick's price:........${it.sellFlick}  Bells"


        })
    }
}