package com.example.fitemotional.ui.components.BNovaEntrada

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
import com.example.fitemotional.ui.theme.HeaderGradientEnd

@Composable
fun IntensitySlider(
    modifier: Modifier = Modifier,
    onIntensityChange: (Int) -> Unit = {}
) {
    var intensity by remember { mutableStateOf(5f) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 🔹 Título com valor atual
            Text(
                text = "Intensidade: ${intensity.toInt()}/10",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.Black,
                    fontSize = 16.sp
                ),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 8.dp)
            )

            // 🔹 Slider de intensidade
            Slider(
                value = intensity,
                onValueChange = {
                    intensity = it
                    onIntensityChange(it.toInt())
                },
                valueRange = 0f..10f,
                steps = 9,
                colors = SliderDefaults.colors(
                    thumbColor = HeaderGradientEnd,
                    activeTrackColor = HeaderGradientStart,
                    inactiveTrackColor = Color.LightGray
                ),
                modifier = Modifier.fillMaxWidth()
            )

            // 🔹 Indicadores de texto
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Leve", color = Color.Gray, fontSize = 14.sp)
                Text("Intenso", color = Color.Gray, fontSize = 14.sp)
            }
        }
    }
}
