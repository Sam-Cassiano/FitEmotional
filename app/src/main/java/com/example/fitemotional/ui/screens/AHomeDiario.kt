package com.example.fitemotional.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fitemotional.data.model.DiaryEntry
import com.example.fitemotional.ui.viewmodel.BNovaEntradaViewModel
import java.time.format.DateTimeFormatter

@Composable
fun AHomeDiario(
    navController: NavController,
    viewModel: BNovaEntradaViewModel
) {
    val entries by viewModel.allEntries.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        DiarioHeader()

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Suas Entradas Recentes",
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (entries.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Nenhuma entrada registrada ainda 🌱",
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(entries) { entry ->
                    DiaryCard(entry)
                }
            }
        }
    }
}

@Composable
fun DiaryCard(entry: DiaryEntry) {
    // Emoji conforme o humor
    val moodEmoji = when (entry.mood.lowercase()) {
        "muito feliz" -> "😁"
        "feliz" -> "😊"
        "neutro" -> "😐"
        "triste" -> "😢"
        "muito triste" -> "😭"
        else -> "🙂"
    }

    // Gradiente translúcido de acordo com o humor
    val gradient = when (entry.mood.lowercase()) {
        "muito feliz" -> Brush.horizontalGradient(
            listOf(
                Color(0xFFFFE082).copy(alpha = 0.2f),
                Color(0xFFFFB74D).copy(alpha = 0.2f)
            )
        )
        "feliz" -> Brush.horizontalGradient(
            listOf(
                Color(0xFFA5D6A7).copy(alpha = 0.2f),
                Color(0xFF81C784).copy(alpha = 0.2f)
            )
        )
        "neutro" -> Brush.horizontalGradient(
            listOf(
                Color(0xFFB0BEC5).copy(alpha = 0.2f),
                Color(0xFFCFD8DC).copy(alpha = 0.2f)
            )
        )
        "triste" -> Brush.horizontalGradient(
            listOf(
                Color(0xFF90CAF9).copy(alpha = 0.2f),
                Color(0xFF64B5F6).copy(alpha = 0.2f)
            )
        )
        "muito triste" -> Brush.horizontalGradient(
            listOf(
                Color(0xFFB39DDB).copy(alpha = 0.2f),
                Color(0xFF9575CD).copy(alpha = 0.2f)
            )
        )
        else -> Brush.horizontalGradient(
            listOf(
                Color(0xFFBDBDBD).copy(alpha = 0.2f),
                Color(0xFFE0E0E0).copy(alpha = 0.2f)
            )
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .background(brush = gradient)
                .padding(16.dp)
        ) {
            // Cabeçalho do cartão
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = "$moodEmoji ${entry.mood}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                    Text(
                        text = entry.date.format(DateTimeFormatter.ofPattern("d 'de' MMMM")),
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }

                Text(
                    text = "${entry.intensity.toInt()}/10",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = Color(0xFF9575CD)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (entry.notes.isNotBlank()) {
                Text(
                    text = entry.notes,
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Chips de atividades
            if (entry.activities.isNotEmpty()) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.wrapContentWidth()
                ) {
                    entry.activities.forEach { activity ->
                        Surface(
                            color = Color(0xFFE0E0E0),
                            shape = RoundedCornerShape(50)
                        ) {
                            Text(
                                text = activity,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                fontSize = 12.sp,
                                color = Color.Black
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
            }

            // Gratidão
            if (entry.gratitude.isNotBlank()) {
                Surface(
                    color = Color(0xFFFFF8E1),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "💛 ${entry.gratitude}",
                        modifier = Modifier.padding(8.dp),
                        fontSize = 13.sp,
                        color = Color(0xFF6D4C41)
                    )
                }
            }
        }
    }
}
