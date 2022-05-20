package com.example.mescompetences.repositories

import android.util.Log
import com.example.mescompetences.models.CompetenceModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import javax.security.auth.callback.Callback

object CompetenceRepository {

    val dbRef = FirebaseDatabase
        .getInstance("https://mes-competences-5d1d3-default-rtdb.europe-west1.firebasedatabase.app")
        .getReference("competences")

    val competences = mutableListOf<CompetenceModel>()

    fun updateAll(callback: () -> Unit): Unit {
        dbRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                competences.clear()
                snapshot.children.forEach {
                    val comp = it.getValue(CompetenceModel::class.java)
                    if(comp != null)
                        competences.add(comp)
                }

                callback()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("CompetenceRepository", error.message)
            }

        })
    }

    fun insert(competence: CompetenceModel): Unit {
        dbRef.child(competence.id).setValue(competence)
    }

    fun remove(competence: CompetenceModel): Unit {
        dbRef.child(competence.id).removeValue()
    }
}

