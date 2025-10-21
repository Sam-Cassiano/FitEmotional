package com.example.fitemotional.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitemotional.ui.theme.HeaderGradientStart
import androidx.compose.foundation.layout.FlowRow

@Composable
fun ActivitiesSelector(
    onSelectionChanged: (List<String>) -> Unit = {}
) {
    val activities = listOf(
        "Trabalho", "Exercício", "Família", "Amigos",
        "Leitura", "Música", "Meditação", "Estudos",
        "Netflix", "Caminhada"
    )

    var selectedActivities by remember { mutableStateOf(setOf<String>()) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // 🔹 Título
            Text(
                text = "O que você fez hoje?",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.Black,
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // 🔹 Chips de atividades
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                activities.forEach { activity ->
                    val isSelected = activity in selectedActivities

                    FilterChip(
                        selected = isSelected,
                        onClick = {
                            selectedActivities = if (isSelected)
                                selectedActivities - activity
                            else
                                selectedActivities + activity
                            onSelectionChanged(selectedActivities.toList())
                        },
                        label = {
                            Text(
                                text = activity,
                                fontSize = 14.sp,
                                color = if (isSelected) Color.White else Color.Black
                            )
                        },
                        shape = RoundedCornerShape(20.dp),
                        colors = FilterChipDefaults.filterChipColors(
                            containerColor = Color.Transparent,
                            selectedContainerColor = HeaderGradientStart.copy(alpha = 0.8f),
                            selectedLabelColor = Color.White
                        ),
                        border = BorderStroke(
                            width = 1.dp,
                            color = if (isSelected) HeaderGradientStart else Color.LightGray
                        )
                    )
                }
            }
        }
    }
}
