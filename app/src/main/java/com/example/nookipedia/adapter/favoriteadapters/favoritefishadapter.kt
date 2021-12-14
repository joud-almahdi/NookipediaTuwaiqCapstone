package com.example.nookipedia.adapter.favoriteadaptersimport

 import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
 import android.widget.ImageView
 import android.widget.TextView
 import androidx.recyclerview.widget.AsyncListDiffer
 import androidx.recyclerview.widget.DiffUtil
 import com.example.nookipedia.R
 import com.example.nookipedia.data.favorites
 import com.example.nookipedia.json.fishjason.fishjsonItem
 import com.example.nookipedia.models.animalcrossingviewmodel
 import com.squareup.picasso.Picasso

class favoritefishadapter() :

    RecyclerView.Adapter<favoritefishadapter.favoritefishviewholder>() {
    val diffcallback= object: DiffUtil.ItemCallback<favorites>()
    {
        override fun areItemsTheSame(oldItem: favorites, newItem: favorites): Boolean {
            return oldItem.crittername==newItem.crittername
        }

        override fun areContentsTheSame(oldItem: favorites, newItem: favorites): Boolean {
            return oldItem==newItem
        }

    }
    private val differ= AsyncListDiffer(this,diffcallback)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): favoritefishadapter.favoritefishviewholder {
        return favoritefishviewholder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.favorite_item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: favoritefishviewholder, position: Int) {
        val item = differ.currentList[position]
        holder.fishcrittername.text=item.crittername
        Picasso.get().load(item.imageurl).into(holder.fishcritterimage)

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    fun submittedlist(array:List<favorites>)
    {
        differ.submitList(array)
    }

    class favoritefishviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fishcrittername:TextView=itemView.findViewById(R.id.nameinfavoritelayout)
        val fishcritterimage:ImageView=itemView.findViewById(R.id.imageinfavoritelayout)
    }
}