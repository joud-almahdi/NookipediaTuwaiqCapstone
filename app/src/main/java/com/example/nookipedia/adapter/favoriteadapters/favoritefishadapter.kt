package com.example.nookipedia.adapter.favoriteadaptersimport

 import android.app.AlertDialog
 import android.content.Context
 import android.content.DialogInterface
 import android.text.InputType
 import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
 import android.widget.EditText
 import android.widget.ImageView
 import android.widget.TextView
 import android.widget.Toast
 import androidx.recyclerview.widget.AsyncListDiffer
 import androidx.recyclerview.widget.DiffUtil
 import com.example.nookipedia.R
 import com.example.nookipedia.data.favorites
 import com.example.nookipedia.json.fishjason.fishjsonItem
 import com.example.nookipedia.models.animalcrossingviewmodel
 import com.google.firebase.auth.ktx.auth
 import com.google.firebase.firestore.FirebaseFirestore
 import com.google.firebase.firestore.ktx.firestore
 import com.google.firebase.ktx.Firebase
 import com.squareup.picasso.Picasso

class favoritefishadapter(val context: Context) :

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

        holder.delete.setOnClickListener {
            delete(item.favid)
            var newlist= mutableListOf<favorites>()
            newlist.addAll(differ.currentList)
            newlist.removeAt(position)
            submittedlist(newlist)

        }


        holder.note.setOnClickListener {
            showdialog(item.favid)
        }



    }


    fun delete(nam:String?)
    {
        val db= FirebaseFirestore.getInstance()
        if (nam != null) {
            db.collection("favorites").document(nam).delete().addOnSuccessListener {
                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
                submittedlist(differ.currentList)
            }
                .addOnFailureListener { e->
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                }

        }

    }





    fun showdialog(id:String?){
        val builder: AlertDialog.Builder = android.app.AlertDialog.Builder(context)
        builder.setTitle("Critternote")


        val input = EditText(context)

        input.setHint("Your note?")
        input.inputType = InputType.TYPE_CLASS_TEXT

        builder.setView(input)


        builder.setPositiveButton("Done", DialogInterface.OnClickListener { dialog, which ->

            var enterednote = input.text.toString()

            val db= FirebaseFirestore.getInstance()
            if (enterednote.isNotEmpty())
            {
                if (id != null) {
                    db.collection("favorites").document(id).update("favnote",enterednote).addOnSuccessListener {
                        Toast.makeText(context, "Updated successfully", Toast.LENGTH_SHORT).show()
                        submittedlist(differ.currentList)
                    }
                        .addOnFailureListener { e->
                            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                        }
                }
            }
            else
            {
                Toast.makeText(context, "Your note was empty, try again", Toast.LENGTH_SHORT).show()
            }


        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()
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
        val delete:ImageView=itemView.findViewById(R.id.deleteinfavoritelayout)
        val note:TextView=itemView.findViewById(R.id.notestextviewinfavoritelayout)
    }
}