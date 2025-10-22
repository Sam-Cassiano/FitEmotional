package com.example.fitemotional.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.fitemotional.data.model.DiaryEntry

/**
 * Banco de dados principal da aplicação FitEmotional.
 * Contém as entidades e fornece acesso aos DAOs.
 */
@Database(
    entities = [DiaryEntry::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    /**
     * DAO responsável pelas operações no diário emocional.
     */
    abstract fun diaryDao(): DiaryDao

    companion object {
        const val DATABASE_NAME = "fitemotional-db"
    }
}
