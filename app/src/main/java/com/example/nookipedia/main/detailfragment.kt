package com.example.nookipedia.main

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.nookipedia.R
import com.example.nookipedia.databinding.FragmentDetailfragmentBinding
import com.example.nookipedia.json.fishjason.fishjsonItem
import com.example.nookipedia.models.addingspeciesviewmodel
import com.example.nookipedia.models.animalcrossingviewmodel
import com.example.nookipedia.models.firebaseviewmodel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso


class detailfragment : Fragment() {
    private lateinit var binding: FragmentDetailfragmentBinding
    private val detailviewmodel:animalcrossingviewmodel by activityViewModels()
    private val fishviewmodel:addingspeciesviewmodel by activityViewModels()
    val auth: FirebaseAuth = Firebase.auth
   private lateinit var thefaves : fishjsonItem

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
        val db=FirebaseFirestore.getInstance()

        detailviewmodel.onefishlivedata.observe(viewLifecycleOwner,{fish->
            binding.catchingphraseindetailview.text=fish.catchphrases[0]
            binding.locationindetailview.text="Location:........${fish.location}"
            binding.nookpriceindetailview.text="Price at Nook's cranny:........${fish.sellNook} Bells"
            binding.shadowsizeindetailview.text="Shadow Size:........${fish.shadowSize}"
            binding.cjprice.text="CJ's price:........${fish.sellCj}  Bells"
            binding.northavailabilityinfish.text="Found on northern hemisphere:........${fish.north.months}"
            binding.northtimeinfish.text=" Times for northern hemisphere :........${fish.north.availabilityArray[0].time}"
            binding.southavailabilityinfish.text="Found on southern hemisphere:........${fish.south.months}"
            binding.southtimeinfish.text=" Times for southern hemisphere :........${fish.south.availabilityArray[0].time}"
            Picasso.get().load(fish.renderUrl).into(binding.itemimageindetailview)
                thefaves=fish


            db.collection("favorites").whereEqualTo("crittername",fish.name).whereEqualTo("userid",auth.currentUser!!.uid).get()
                .addOnSuccessListener {
                    if(it.count()>0)
                    {
                        binding.favoriteindetailview.setImageResource(R.drawable.whitecoin)
                    }
                    else
                    {
                        binding.favoriteindetailview.setImageResource(R.drawable.coin)
                    }
                }


            binding.shareimageview.setOnClickListener {
                val intent=Intent(Intent.ACTION_SEND)
                intent.setType("text/plain")
                intent.putExtra(Intent.EXTRA_TEXT,fish.url)
                startActivity(Intent.createChooser(intent, "Share link using"))
            }

        })





        binding.favoriteindetailview.setOnClickListener { click->

            val id=thefaves.number.toString()
            fishviewmodel.checknewfavebeforeadd(thefaves.name,id)
            binding.favoriteindetailview.setImageResource(R.drawable.whitecoin)
            Log.d("tag",thefaves.name)

        }


        fishviewmodel.livedatafortoasts.observe(viewLifecycleOwner,{
            it?.let {
                Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
                fishviewmodel.livedatafortoasts.postValue(null)
            }

        })



       fishviewmodel.firebaseerrordata.observe(viewLifecycleOwner,{

           it?.let {
               Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
               fishviewmodel.firebaseerrordata.postValue(null)
           }


        })




    }



}





//val db=FirebaseFirestore.getInstance()
//
//db.collection("favorites").whereEqualTo("crittername",it.name).whereEqualTo("userid",auth.currentUser!!.uid).get().addOnSuccessListener {
//    if(it.count()>0)
//    {
//        Toast.makeText(requireActivity(), "This critter already exists", Toast.LENGTH_SHORT).show()
//    }
//    else
//    {
//        val fave:MutableMap<String,Any> = hashMapOf(
//            "userid" to auth.currentUser!!.uid,
//            "crittername" to fish.name,
//            "imageurl" to fish.imageUrl,
//            "favnote" to "",
//            "favid" to fish.number.toString()
//        )
//
//
//        db.collection("favorites").document(id)
//            .set(fave)
//            .addOnSuccessListener {
//                Toast.makeText(requireActivity(), "Success", Toast.LENGTH_SHORT).show()}
//            .addOnFailureListener { e ->
//                Toast.makeText(requireActivity(), e.message, Toast.LENGTH_SHORT).show() }
//    }
//
//
//}