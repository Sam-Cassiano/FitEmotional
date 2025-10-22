package com.example.fitemotional.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitemotional.data.model.DiaryEntry
import kotlinx.coroutines.flow.Flow

/**
 * DAO responsável pelas operações no banco de dados de entradas do diário.
 */
@Dao
interface DiaryDao {

    /**
     * Insere uma nova entrada no diário.
     * Caso já exista uma entrada com a mesma data, ela será substituída.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: DiaryEntry)

    /**
     * Retorna todas as entradas salvas, ordenadas da mais recente para a mais antiga.
     */
    @Query("SELECT * FROM entries ORDER BY date DESC")
    fun getAllEntries(): Flow<List<DiaryEntry>>
}
