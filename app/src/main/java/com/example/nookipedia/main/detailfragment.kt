package com.example.nookipedia.main

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.nookipedia.R
import com.example.nookipedia.databinding.FragmentDetailfragmentBinding
import com.example.nookipedia.models.animalcrossingviewmodel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso


class detailfragment : Fragment() {
    private lateinit var binding: FragmentDetailfragmentBinding
    private val detailviewmodel:animalcrossingviewmodel by activityViewModels()
    val auth: FirebaseAuth = Firebase.auth

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
        val sound: MediaPlayer = MediaPlayer.create(requireActivity(),R.raw.obtain)
        sound.start()



    }


    @SuppressLint("SetTextI18n")
    fun observers()
    {
        detailviewmodel.onefishlivedata.observe(viewLifecycleOwner,{fish->
            binding.catchingphraseindetailview.text=fish.catchphrases[0]
            binding.locationindetailview.text="Location:........${fish.location}"
            binding.nookpriceindetailview.text="Price at Nook's cranny:........${fish.sellNook} Bells"
            binding.shadowsizeindetailview.text="Shadow Size:........${fish.shadowSize}"
            binding.cjprice.text="CJ's price:........${fish.sellCj}  Bells"
            Picasso.get().load(fish.renderUrl).into(binding.itemimageindetailview)

            binding.favoriteindetailview.setOnClickListener { click->
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
                        fave["favid"]=fish.number.toString()
                        db.collection("favorites").add(fave).addOnSuccessListener {
                            Toast.makeText(requireActivity(), "Success", Toast.LENGTH_SHORT).show()
                        }
                            .addOnFailureListener{
                                Toast.makeText(requireActivity(), it.message.toString(), Toast.LENGTH_SHORT).show()
                            }
                    }


                }


            }
            binding.shareimageview.setOnClickListener {
                val intent=Intent(Intent.ACTION_SEND)
                intent.setType("text/plain")
                intent.putExtra(Intent.EXTRA_TEXT,fish.url)
                startActivity(Intent.createChooser(intent, "Share link using"))
            }

        })




    }



}