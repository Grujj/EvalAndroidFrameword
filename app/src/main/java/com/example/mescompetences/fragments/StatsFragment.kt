package com.example.mescompetences.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mescompetences.MainActivity
import com.example.mescompetences.R
import com.example.mescompetences.adapters.CompetenceAdapter
import com.example.mescompetences.models.CompetenceModel
import com.example.mescompetences.repositories.CompetenceRepository
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
        handleAverageLevelView(view)
        handleTotalLevelView(view)
        handleLastCompetenceView(view)

        return view
    }

    /**
     * Methode qui gere la view de la competence avec le plus haut niveau
     */
    private fun handleTopCompetenceView(view: View): Unit {

        val topCompetence = CompetenceRepository.competences
            .reduce{ a, b -> if(a.level < b.level) b else a}

        val recycler = view.findViewById<RecyclerView>(R.id.layout_top_competence)
        recycler.adapter = CompetenceAdapter(listOf(topCompetence))
    }

    /**
     * Methode qui gere la view de la moyenne des niveaux de toutes les competences
     */
    private fun handleAverageLevelView(view: View): Unit {

        val averageLevel = CompetenceRepository.competences
            .map { competence -> competence.level }
            .reduce { a, b -> a + b }
            .div(CompetenceRepository.competences.size)
            .toString()

        val averageLevelView = view.findViewById<TextView>(R.id.stats_average_level)
        averageLevelView.text = averageLevel
    }

    /**
     * Methode qui gere la view du niveau total de toutes les competences
     */
    private fun handleTotalLevelView(view: View): Unit {

        val totalLevel = CompetenceRepository.competences
            .map { competence -> competence.level }
            .reduce { a, b -> a + b }
            .toString()

        val totalLevelView = view.findViewById<TextView>(R.id.stats_total_level)
        totalLevelView.text = totalLevel
    }

    /**
     * Methode qui gere la view de la derniere date mise a jour.
     */
    private fun handleLastCompetenceView(view: View): Unit {

        val lastCompetenceUpdated = CompetenceRepository.competences
            .reduce{ a, b ->
                if(firstDateIsBefore(a, b)) b else a
            }

        val recycler = view.findViewById<RecyclerView>(R.id.recycler_last_competence)
        recycler.adapter = CompetenceAdapter(listOf(lastCompetenceUpdated))
    }

    /**
     * Methode qui recupere la date d une competence, la parse et la compare avec une autre.
     * Si la premiere date est avant la deuxieme, renvoi true
     * Sinon false
     */
    private fun firstDateIsBefore(date1: CompetenceModel, date2: CompetenceModel): Boolean{
        return LocalDateTime.parse(date1.updateDate).isBefore(LocalDateTime.parse(date2.updateDate))
    }
}