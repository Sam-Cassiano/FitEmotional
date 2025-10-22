package com.example.fitemotional

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.fitemotional.data.local.AppDatabase
import com.example.fitemotional.navigation.NavGraph
import com.example.fitemotional.ui.theme.FitEmotionalTheme
import com.example.fitemotional.ui.viewmodel.BNovaEntradaViewModel

class MainActivity : ComponentActivity() {

    private val bNovaEntradaViewModel: BNovaEntradaViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val database = Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java,
                    "fitemotional-db"
                )
                    // ⚠️ apenas enquanto desenvolve; depois, remova
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()

                @Suppress("UNCHECKED_CAST")
                return BNovaEntradaViewModel(database.diaryDao()) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FitEmotionalTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    NavGraph(bNovaEntradaViewModel = bNovaEntradaViewModel)
                }
            }
        }
    }
}
