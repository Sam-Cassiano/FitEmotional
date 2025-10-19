package com.example.fitemotional

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import com.example.fitemotional.navigation.NavGraph
import com.example.fitemotional.ui.theme.FitEmotionalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitEmotionalTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    NavGraph()
                }
            }
        }
    }
}
