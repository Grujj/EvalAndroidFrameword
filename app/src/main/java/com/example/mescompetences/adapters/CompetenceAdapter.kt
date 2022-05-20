package com.example.mescompetences.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mescompetences.R
import com.example.mescompetences.models.CompetenceModel
import com.example.mescompetences.popups.CompetenceDetailsPopup

class CompetenceAdapter(
    val liste: List<CompetenceModel>
): RecyclerView.Adapter<CompetenceAdapter.CompetenceViewHolder>() {

    class CompetenceViewHolder(view: View): RecyclerView.ViewHolder(view){
        val compNameView = view.findViewById<TextView>(R.id.comp_name)
        val compLvlView = view.findViewById<TextView>(R.id.comp_lvl)
        val compTagView = view.findViewById<RecyclerView>(R.id.tag_recycler_view)
        val compDescription = view.findViewById<TextView>(R.id.comp_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetenceViewHolder {
        val holder = CompetenceViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_competence, parent, false)
        )

        /* Mise en forme des tags en grille */
        holder.compTagView.layoutManager = GridLayoutManager(parent.context, 3)

        return holder
    }

    override fun onBindViewHolder(holder: CompetenceViewHolder, position: Int) {
        val comp = liste[position]
        holder.compNameView.text = comp.name
        val niveau = "niveau ${comp.level}"
        holder.compLvlView.text = niveau
        holder.compTagView.adapter = TagAdapter(comp.tags)
        holder.compDescription.text = comp.description
        holder.itemView.setOnClickListener {
            CompetenceDetailsPopup(it.context, comp).show()
        }
    }

    override fun getItemCount(): Int = liste.size
}