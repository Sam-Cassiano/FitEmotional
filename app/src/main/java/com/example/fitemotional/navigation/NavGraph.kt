package com.example.fitemotional.navigation

import BNovaEntrada
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitemotional.ui.components.BottomBar
import com.example.fitemotional.ui.screens.*

@Composable
fun NavGraph(startDestination: String = "homeDiario") {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = androidx.compose.ui.Modifier.padding(innerPadding)
        ) {
            composable("homeDiario") { AHomeDiario(navController) }
            composable("novaEntrada") { BNovaEntrada(navController) }
            composable("estatisticas") { CEstatisticas(navController) }
            composable("perfil") { CPerfil(navController) }
        }
    }
}
