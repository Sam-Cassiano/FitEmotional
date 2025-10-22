package com.example.fitemotional.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "entries")
data class DiaryEntry(
    @PrimaryKey val date: LocalDate,
    val mood: String,
    val intensity: Float,
    val activities: List<String>,
    val notes: String,
    val gratitude: String
)
