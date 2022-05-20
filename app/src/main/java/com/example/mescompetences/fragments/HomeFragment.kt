package com.example.mescompetences.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mescompetences.MainActivity
import com.example.mescompetences.adapters.CompetenceAdapter
import com.example.mescompetences.R
import com.example.mescompetences.models.CompetenceModel
import com.example.mescompetences.repositories.CompetenceRepository

class HomeFragment(): Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_page, container, false)

        val compList = CompetenceRepository.competences
        val emptyListView = view.findViewById<TextView>(R.id.empty_list_competences)

        if(compList.isEmpty()){
            emptyListView.text = resources.getString(R.string.home_empty_list_competences)
        }
        else {
            emptyListView.text = ""
            val recycler = view.findViewById<RecyclerView>(R.id.recycler_view)
            recycler.adapter = CompetenceAdapter(compList)
        }

        return view
    }
}