package com.example.mescompetences.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mescompetences.R

class TagAdapter(
    private val liste: List<String>
): RecyclerView.Adapter<TagAdapter.TagViewHolder>() {

    class TagViewHolder(view: View): RecyclerView.ViewHolder(view){
        val compTag = view.findViewById<TextView>(R.id.tag)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        return TagViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_tag, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.compTag.text = liste[position]
    }

    override fun getItemCount(): Int = liste.size
}