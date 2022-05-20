package com.example.mescompetences.repositories

import android.util.Log
import com.example.mescompetences.models.CompetenceModel
import com.google.firebase.database.*
import kotlin.reflect.KClass

class Repository<T : Any>(private val dbRef: DatabaseReference, val c: T) {

    val values = mutableListOf<Any?>()

    fun updateAll(callback: () -> Unit): Unit {
        dbRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                values.clear()
                snapshot.children.forEach {
                    val value = it.getValue(c.javaClass)
                    if(value != null)
                        values.add(value)
                }

                callback()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Repository", error.message)
            }
        })
    }
}