package com.example.mescompetences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mescompetences.fragments.HomeFragment
import com.example.mescompetences.fragments.NewCompetenceFragment
import com.example.mescompetences.fragments.StatsFragment
import com.example.mescompetences.repositories.CompetenceRepository
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navMenu = findViewById<BottomNavigationView>(R.id.nav_menu)

        loadFragment(HomeFragment(), R.id.page_title)

        navMenu.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home_page -> {
                    loadFragment(HomeFragment(), R.id.page_title)
                    return@setOnItemSelectedListener true
                }
                R.id.new_competence -> {
                    loadFragment(NewCompetenceFragment(this), R.id.page_title)
                    return@setOnItemSelectedListener true
                }
                R.id.stats -> {
                    loadFragment(StatsFragment(this), R.id.page_title)
                    return@setOnItemSelectedListener true
                }
                else -> false
            }
        }
    }

    fun loadFragment(fragment: Fragment, titleId: Int) {
        val pageTitleView = findViewById<TextView>(titleId)
        pageTitleView.text = resources.getString(titleId)
        CompetenceRepository.updateAll {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}