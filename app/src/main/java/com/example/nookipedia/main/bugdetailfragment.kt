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
import com.example.nookipedia.databinding.FragmentBugdetailfragmentBinding
import com.example.nookipedia.json.bugjson.bugjsonItem
import com.example.nookipedia.json.fishjason.fishjsonItem
import com.example.nookipedia.models.addingbugsviewmodel
import com.example.nookipedia.models.addingspeciesviewmodel
import com.example.nookipedia.models.animalcrossingviewmodel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso


class bugdetailfragment : Fragment() {
private lateinit var binding:FragmentBugdetailfragmentBinding
private val bugviewmodel:animalcrossingviewmodel by activityViewModels()
    private val addbugviewmodel: addingbugsviewmodel by activityViewModels()
    private lateinit var thefaves : bugjsonItem
    val auth: FirebaseAuth = Firebase.auth

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
        val db=FirebaseFirestore.getInstance()
        bugviewmodel.onebuglivedata.observe(viewLifecycleOwner,{fish->
            binding.catchingphraseinbugdetailview.text=fish.catchphrases[0]
            Picasso.get().load(fish.renderUrl).into(binding.itemimageinbugdetailview)
            binding.locationinbugdetailview.text="Location:........${fish.location}"
            binding.nookpriceinbugdetailview.text="Price at Nook's cranny:........${fish.sellNook} Bells"
            binding.flickprice.text="Flick's price:........${fish.sellFlick}  Bells"
            binding.northavailabilityinbug.text="Found on northern hemisphere:........${fish.north.months}"
            binding.northtimeinbug.text=" Times for northern hemisphere :........${fish.north.availabilityArray[0].time}"
            binding.southavailabilityinbug.text="Found on southern hemisphere:........${fish.south.months}"
            binding.southtimeinbug.text=" Times for southern hemisphere :........${fish.south.availabilityArray[0].time}"

            thefaves=fish


            db.collection("favorites").whereEqualTo("crittername",fish.name).whereEqualTo("userid",auth.currentUser!!.uid).get()
                .addOnSuccessListener {
                    if(it.count()>0)
                    {
                        binding.favoriteinbugdetailview.setImageResource(R.drawable.whitecoin)
                    }
                    else
                    {
                        binding.favoriteinbugdetailview.setImageResource(R.drawable.coin)
                    }
                }


            binding.shareimageinbugview.setOnClickListener {
                val intent= Intent(Intent.ACTION_SEND)
                intent.setType("text/plain")
                intent.putExtra(Intent.EXTRA_TEXT,fish.url)
                startActivity(Intent.createChooser(intent, "Share link using"))
            }


        })


        binding.favoriteinbugdetailview.setOnClickListener { click->
            addbugviewmodel.checknewfavebeforeadd(thefaves.name,thefaves.number.toString())
            binding.favoriteinbugdetailview.setImageResource(R.drawable.whitecoin)
        }

        addbugviewmodel.livedatafortoasts.observe(viewLifecycleOwner,{
            it?.let {
                Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
                addbugviewmodel.livedatafortoasts.postValue(null)
            }

        })



        addbugviewmodel.firebaseerrordata.observe(viewLifecycleOwner,{

            it?.let {
                Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
                addbugviewmodel.firebaseerrordata.postValue(null)
            }


        })


    }
}