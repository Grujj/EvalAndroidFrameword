package com.example.mescompetences.models

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class CompetenceModel(
    val id: String = UUID.randomUUID().toString(),
    val name: String = "UNKNOWN",
    private var _level: Int = 0,
    val tags: List<String> = listOf(),
    val description: String = "",
    val updateDate: String = LocalDateTime.now().toString()
){
    var level: Int
        get() = _level
        set(newLevel: Int) {
            _level = if(newLevel < 0) 0
                else if(newLevel > 20) 20
                else newLevel
    }
}