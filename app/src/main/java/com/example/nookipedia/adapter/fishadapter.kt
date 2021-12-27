package com.example.nookipedia.adapterimport

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.nookipedia.R
import com.example.nookipedia.json.fishjason.fishjsonItem
import com.example.nookipedia.models.addingspeciesviewmodel
import com.example.nookipedia.models.animalcrossingviewmodel
import com.squareup.picasso.Picasso

class fishadapter(val viewModel: animalcrossingviewmodel,val fishdetail:addingspeciesviewmodel) :
    RecyclerView.Adapter<fishadapter.fishviewvholder>() {
    val diffcallback= object: DiffUtil.ItemCallback<fishjsonItem>()
    {
        override fun areItemsTheSame(oldItem: fishjsonItem, newItem: fishjsonItem): Boolean {
            return oldItem.url==newItem.url
        }

        override fun areContentsTheSame(oldItem: fishjsonItem, newItem: fishjsonItem): Boolean {
            return oldItem==newItem
        }

    }
    private val differ= AsyncListDiffer(this,diffcallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): fishadapter.fishviewvholder {
        return fishviewvholder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: fishviewvholder, position: Int) {
        val item = differ.currentList[position]
        holder.nameoffish.text=item.name
        Picasso.get().load(item.imageUrl).into(holder.iconoffish)

        holder.itemView.setOnClickListener {
            viewModel.onefishlivedata.postValue(item)
            fishdetail.thefaves=item

            holder.itemView.findNavController().navigate(R.id.action_fishfragment_to_detailfragment)
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

fun submitfish(array:List<fishjsonItem>)
{
    differ.submitList(array)
}

    class fishviewvholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameoffish:TextView=itemView.findViewById(R.id.itemnameinitemlayout)
        var iconoffish:ImageView=itemView.findViewById(R.id.itemimageinitemlayout)
    }
}