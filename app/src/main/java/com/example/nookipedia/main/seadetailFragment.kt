package com.example.nookipedia.main

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.nookipedia.R
import com.example.nookipedia.adapterimport.seaadapter
import com.example.nookipedia.databinding.FragmentSeadetailBinding
import com.example.nookipedia.json.fishjason.fishjsonItem
import com.example.nookipedia.json.seajson.seajsonItem
import com.example.nookipedia.models.addingbugsviewmodel
import com.example.nookipedia.models.addingseaviewmodel
import com.example.nookipedia.models.animalcrossingviewmodel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso


class seadetailFragment : Fragment() {

    private lateinit var binding:FragmentSeadetailBinding
    private val seaviewmodel:animalcrossingviewmodel by activityViewModels()
    private val addseaviewmodel: addingseaviewmodel by activityViewModels()
    val auth: FirebaseAuth = Firebase.auth
    private lateinit var thefaves : seajsonItem

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
        val sound: MediaPlayer = MediaPlayer.create(requireActivity(),R.raw.obtain)
        sound.start()


    }

    @SuppressLint("SetTextI18n")
    fun observers()
    {
        seaviewmodel.onesealivedata.observe(viewLifecycleOwner,{fish->
            binding.catchingphraseinseadetailview.text=fish.catchphrases[0]
            Picasso.get().load(fish.renderUrl).into(binding.itemimageinseadetailview)
            binding.movementspeedinseadetail.text="Movement Speed:........${fish.shadowMovement}"
            binding.shadowsizeinseadetailview.text="Shadow Size:........${fish.shadowSize}"
            binding.nookpriceinseadetailview.text="Price at Nook's cranny:........${fish.sellNook} Bells"


           thefaves=fish

            binding.shareimageinseaview.setOnClickListener {
                val intent= Intent(Intent.ACTION_SEND)
                intent.setType("text/plain")
                intent.putExtra(Intent.EXTRA_TEXT,fish.url)
                startActivity(Intent.createChooser(intent, "Share link using"))
            }
        })



        binding.favoriteinseadetailview.setOnClickListener { click->
           addseaviewmodel.checknewfavebeforeadd(thefaves.name,thefaves.number.toString())
        }






        addseaviewmodel.livedatafortoasts.observe(viewLifecycleOwner,{
            it?.let {
                Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
                addseaviewmodel.livedatafortoasts.postValue(null)
            }

        })



        addseaviewmodel.firebaseerrordata.observe(viewLifecycleOwner,{

            it?.let {
                Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
                addseaviewmodel.firebaseerrordata.postValue(null)
            }


        })






    }








}