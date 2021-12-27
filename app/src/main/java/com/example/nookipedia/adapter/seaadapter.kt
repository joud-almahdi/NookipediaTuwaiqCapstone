package com.example.nookipedia.adapterimport

import androidx.recyclerview.widget.RecyclerView
import com.example.nookipedia.json.seajson.seajsonItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.nookipedia.R
import com.example.nookipedia.json.bugjson.bugjsonItem
import com.example.nookipedia.models.addingseaviewmodel
import com.example.nookipedia.models.animalcrossingviewmodel
import com.squareup.picasso.Picasso

class seaadapter(val seaviewmodel:animalcrossingviewmodel,val seadetails:addingseaviewmodel) :
    RecyclerView.Adapter<seaadapter.seaviewholder>() {


    val diffcallback= object: DiffUtil.ItemCallback<seajsonItem>(){
        override fun areItemsTheSame(oldItem: seajsonItem, newItem: seajsonItem): Boolean {
            return oldItem.url==newItem.url
        }

        override fun areContentsTheSame(oldItem: seajsonItem, newItem: seajsonItem): Boolean {
            return oldItem==newItem
        }


    }
    private val differ= AsyncListDiffer(this,diffcallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): seaadapter.seaviewholder {
        return seaviewholder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: seaviewholder, position: Int) {
        val item = differ.currentList[position]
       holder.seaname.text=item.name
        Picasso.get().load(item.imageUrl).into(holder.seaimage)

        holder.itemView.setOnClickListener {
            seaviewmodel.onesealivedata.postValue(item)
            seadetails.thefaves=item
            holder.itemView.findNavController().navigate(R.id.action_seafragment_to_seadetailFragment)

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitsea(array:List<seajsonItem>)
    {
      differ.submitList(array)
    }

    class seaviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var seaimage: ImageView =itemView.findViewById(R.id.itemimageinitemlayout)
        var seaname: TextView =itemView.findViewById(R.id.itemnameinitemlayout)
    }
}