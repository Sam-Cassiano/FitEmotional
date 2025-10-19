package com.example.fitemotional.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

data class BottomNavItem(
    val route: String,
    val title: String,
    val icon: @Composable () -> Unit
)

@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(
        BottomNavItem("homeDiario", "Diário", { Icon(Icons.Filled.Bookmark, contentDescription = null) }),
        BottomNavItem("novaEntrada", "Nova Entrada", { Icon(Icons.Filled.AddCircle, contentDescription = null) }),
        BottomNavItem("estatisticas", "Estatísticas", { Icon(Icons.Filled.Assessment, contentDescription = null) }),
        BottomNavItem("perfil", "Perfil", { Icon(Icons.Filled.Person, contentDescription = null) }),
    )

    NavigationBar(
        containerColor = Color.White,
        contentColor = MaterialTheme.colorScheme.primary
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = item.icon,
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo("homeDiario") { inclusive = false }
                            launchSingleTop = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray
                )
            )
        }
    }
}
