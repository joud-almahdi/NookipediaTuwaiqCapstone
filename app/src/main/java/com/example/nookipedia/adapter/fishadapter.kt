package com.example.nookipedia.adapterimport

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.nookipedia.R
import com.example.nookipedia.json.fishjason.fishjsonItem
import com.squareup.picasso.Picasso

class fishadapter(private val list: List<fishjsonItem>) :
    RecyclerView.Adapter<fishadapter.fishviewvholder>() {
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
        val item = list[position]
        holder.nameoffish.text=item.name
        Picasso.get().load(item.imageUrl).into(holder.iconoffish)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class fishviewvholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameoffish:TextView=itemView.findViewById(R.id.itemnameinitemlayout)
        var iconoffish:ImageView=itemView.findViewById(R.id.itemimageinitemlayout)
    }
}