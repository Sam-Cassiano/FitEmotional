package com.example.fitemotional.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitemotional.data.local.DiaryDao
import com.example.fitemotional.data.model.DiaryEntry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.time.LocalDate

/**
 * ViewModel responsável por gerenciar as operações do diário.
 * Lida com a inserção e leitura das entradas no banco de dados Room.
 */
class BNovaEntradaViewModel(private val diaryDao: DiaryDao) : ViewModel() {

    /**
     * Fluxo com todas as entradas do diário.
     * É observado em tempo real pela tela AHomeDiario.
     */
    val allEntries: Flow<List<DiaryEntry>> = diaryDao.getAllEntries()

    /**
     * Salva uma nova entrada no diário.
     *
     * @param date Data da entrada.
     * @param mood Humor selecionado.
     * @param intensity Intensidade do humor (0-10).
     * @param activities Lista de atividades realizadas.
     * @param notes Notas e reflexões.
     * @param gratitude Registro de gratidão.
     */
    fun salvarEntrada(
        date: LocalDate,
        mood: String,
        intensity: Float,
        activities: List<String>,
        notes: String,
        gratitude: String
    ) {
        val entry = DiaryEntry(
            date = date,
            mood = mood,
            intensity = intensity,
            activities = activities,
            notes = notes,
            gratitude = gratitude
        )

        // Inserção assíncrona
        viewModelScope.launch {
            diaryDao.insert(entry)
        }
    }
}
