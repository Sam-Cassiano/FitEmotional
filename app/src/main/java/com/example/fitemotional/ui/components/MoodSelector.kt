package com.example.fitemotional.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitemotional.ui.theme.HeaderGradientEnd
import com.example.fitemotional.ui.theme.HeaderGradientStart
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow

data class MoodOption(val emoji: String, val label: String)

@Composable
fun MoodSelector(
    modifier: Modifier = Modifier,
    onMoodSelected: (MoodOption) -> Unit
) {
    val moods = listOf(
        MoodOption("😄", "Muito Feliz"),
        MoodOption("😊", "Feliz"),
        MoodOption("😐", "Neutro"),
        MoodOption("😔", "Triste"),
        MoodOption("😢", "Muito Triste")
    )

    var selectedMood by remember { mutableStateOf<MoodOption?>(null) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(2.dp, RoundedCornerShape(16.dp))
            .background(Color.White, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // 🔹 Título
            Text(
                text = "Como você se sente?",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // 🔹 Grade de emoções
            FlowRow(
                maxItemsInEachRow = 2,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                moods.forEach { mood ->
                    val isSelected = selectedMood == mood

                    val background = if (isSelected)
                        Brush.linearGradient(listOf(HeaderGradientStart, HeaderGradientEnd))
                    else
                        Brush.linearGradient(listOf(Color.White, Color.White))

                    Card(
                        modifier = Modifier
                            .width(150.dp)
                            .height(80.dp)
                            .clickable {
                                selectedMood = mood
                                onMoodSelected(mood)
                            },
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                        elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 4.dp else 1.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .background(background, shape = RoundedCornerShape(12.dp))
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = mood.emoji,
                                    fontSize = 24.sp,
                                    modifier = Modifier.padding(bottom = 4.dp)
                                )
                                Text(
                                    text = mood.label,
                                    fontSize = 14.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    color = if (isSelected) Color.White else Color.Black,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
