package com.example.nookipedia.adapterimport

import androidx.recyclerview.widget.RecyclerView
import com.example.nookipedia.json.seajson.seajsonItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nookipedia.R

class seaadapter(private val list: List<seajsonItem>) :
    RecyclerView.Adapter<seaadapter.seaviewholder>() {
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
        val item = list[position]
        TODO("bind view with data")
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class seaviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }
}