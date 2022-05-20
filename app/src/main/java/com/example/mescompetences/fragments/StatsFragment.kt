package com.example.mescompetences.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mescompetences.MainActivity
import com.example.mescompetences.R
import com.example.mescompetences.adapters.CompetenceAdapter
import com.example.mescompetences.repositories.CompetenceRepository
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class StatsFragment(
    val mainActivity: MainActivity
): Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stats, container, false)

        handleTopCompetenceView(view)
        handleTotalLevelView(view)
        handleLastCompetenceView(view)

        return view
    }

    private fun handleTopCompetenceView(view: View): Unit {

        val comp = CompetenceRepository.competences
            .reduce{ a, b -> if(a.level < b.level) b else a}

        val recycler = view.findViewById<RecyclerView>(R.id.layout_top_competence)
        recycler.adapter = CompetenceAdapter(listOf(comp))
    }

    private fun handleTotalLevelView(view: View): Unit {

        val totalLevel = CompetenceRepository.competences
            .map { competence -> competence.level }
            .reduce { a, b -> a + b }
            .toString()

        val totalLevelView = view.findViewById<TextView>(R.id.stats_total_level)
        totalLevelView.text = totalLevel
    }

    private fun handleLastCompetenceView(view: View): Unit {

        val comp = CompetenceRepository.competences
            .reduce{ a, b ->
                if(LocalDateTime.parse(a.updateDate).isBefore(LocalDateTime.parse(b.updateDate))) b else a
            }

        val recycler = view.findViewById<RecyclerView>(R.id.layout_last_competence)
        recycler.adapter = CompetenceAdapter(listOf(comp))
    }


}