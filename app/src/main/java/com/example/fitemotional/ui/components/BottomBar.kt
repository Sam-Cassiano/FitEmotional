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
import com.example.fitemotional.ui.theme.HeaderGradientStart

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
        containerColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            val isSelected = currentRoute == item.route

            NavigationBarItem(
                icon = item.icon,
                label = {
                    Text(
                        text = item.title,
                        color = if (isSelected) HeaderGradientStart else Color.Gray
                    )
                },
                selected = isSelected,
                onClick = {
                    if (!isSelected) {
                        navController.navigate(item.route) {
                            popUpTo("homeDiario") { inclusive = false }
                            launchSingleTop = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = HeaderGradientStart,
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = HeaderGradientStart,
                    unselectedTextColor = Color.Gray
                )
            )
        }
    }
}
