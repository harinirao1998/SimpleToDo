package com.example.simpletodo
//bridge that tells the recycler view what to do with the data we give to the MainActivity kotlin file
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.R
import android.util.Log

import android.view.LayoutInflater
import android.widget.Button

import android.widget.TextView

class TaskItemAdapter(val listOfItems: List<String>, val longClickListener: OnLongClickListener):
    RecyclerView.Adapter<TaskItemAdapter .ViewHolder>(){

    interface OnLongClickListener{
        fun onItemLongClicked(position:Int)
    }

    //tells recycler view how to layout each particular view..
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //get data model based on position
        val item=listOfItems.get(position)

        holder.textView.text=item
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }

    //looking at layout, grab a reference to text view and display that text view
    //stores references to the elements in the layoutview
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //store references to our views
        val textView: TextView
        init{
            textView=itemView.findViewById(android.R.id.text1)

            itemView.setOnLongClickListener {
                longClickListener.onItemLongClicked(adapterPosition)
                true
            }
        }
    }

}