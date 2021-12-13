package com.example.nookipedia.adapterimport

import com.example.nookipedia.json.bugjson.bugjsonItem

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.nookipedia.R
import com.example.nookipedia.json.fishjason.fishjsonItem
import com.example.nookipedia.models.animalcrossingviewmodel
import com.squareup.picasso.Picasso

class bugadapter(val viewModel: animalcrossingviewmodel) :
    RecyclerView.Adapter<bugadapter.bugviewmodel>() {
    val diffcallback= object: DiffUtil.ItemCallback<bugjsonItem>(){
        override fun areItemsTheSame(oldItem: bugjsonItem, newItem: bugjsonItem): Boolean {
            return oldItem.url==newItem.url
        }

        override fun areContentsTheSame(oldItem: bugjsonItem, newItem: bugjsonItem): Boolean {
            return oldItem==newItem
        }

    }
    private val differ= AsyncListDiffer(this,diffcallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): bugadapter.bugviewmodel {
        return bugviewmodel(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: bugviewmodel, position: Int) {
        val item = differ.currentList[position]
        holder.bugname.text=item.name
        Picasso.get().load(item.imageUrl).into(holder.bugimage)

        holder.itemView.setOnClickListener {
           viewModel.onebuglivedata.postValue(item)
            holder.itemView.findNavController().navigate(R.id.action_bugfragment_to_bugdetailfragment)
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitbug(array:List<bugjsonItem>)
    {
        differ.submitList(array)
    }

    class bugviewmodel(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var bugname:TextView=itemView.findViewById(R.id.itemnameinitemlayout)
        var bugimage:ImageView=itemView.findViewById(R.id.itemimageinitemlayout)

    }
}