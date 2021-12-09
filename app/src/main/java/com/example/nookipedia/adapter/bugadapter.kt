package com.example.nookipedia.adapterimport

import com.example.nookipedia.json.bugjson.bugjsonItem

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nookipedia.R

class bugadapter(private val list: List<bugjsonItem>) :
    RecyclerView.Adapter<bugadapter.bugviewmodel>() {
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
        val item = list[position]
        TODO("bind view with data")
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class bugviewmodel(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }
}