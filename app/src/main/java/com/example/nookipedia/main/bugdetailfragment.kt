package com.example.nookipedia.main

import android.annotation.SuppressLint
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
import com.example.nookipedia.models.animalcrossingviewmodel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso


class bugdetailfragment : Fragment() {
private lateinit var binding:FragmentBugdetailfragmentBinding
private val bugviewmodel:animalcrossingviewmodel by activityViewModels()
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
        bugviewmodel.onebuglivedata.observe(viewLifecycleOwner,{fish->
            binding.catchingphraseinbugdetailview.text=fish.catchphrases[0]
            Picasso.get().load(fish.renderUrl).into(binding.itemimageinbugdetailview)
            binding.locationinbugdetailview.text="Location:........${fish.location}"
            binding.nookpriceinbugdetailview.text="Price at Nook's cranny:........${fish.sellNook} Bells"
            binding.nookpriceinbugdetailview.text="Flick's price:........${fish.sellFlick}  Bells"

            binding.favoriteinbugdetail.setOnClickListener { click->
                val db=FirebaseFirestore.getInstance()
                val fave:MutableMap<String,Any> = hashMapOf()


                db.collection("favorites").whereEqualTo("crittername",fish.name).whereEqualTo("userid",auth.currentUser!!.uid).get().addOnSuccessListener {
                    if(it.count()>0)
                    {
                        Toast.makeText(requireActivity(), "This critter already exists", Toast.LENGTH_SHORT).show()
                    }
                    else
                    {
                        fave["crittername"]=fish.name
                        fave["imageurl"]=fish.imageUrl
                        fave["userid"]=auth.currentUser!!.uid
                        db.collection("favorites").add(fave).addOnSuccessListener {
                            Toast.makeText(requireActivity(), "Success", Toast.LENGTH_SHORT).show()
                        }
                            .addOnFailureListener{
                                Toast.makeText(requireActivity(), it.message.toString(), Toast.LENGTH_SHORT).show()
                            }
                    }


                }
            }


        })
    }
}