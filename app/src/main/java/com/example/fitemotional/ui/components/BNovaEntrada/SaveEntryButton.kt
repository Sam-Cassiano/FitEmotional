package com.example.fitemotional.ui.components.BNovaEntrada

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitemotional.ui.viewmodel.BNovaEntradaViewModel
import kotlinx.coroutines.launch

@Composable
fun SaveEntryButton(
    viewModel: BNovaEntradaViewModel,   // ViewModel para salvar a entrada
    selectedDate: java.time.LocalDate,
    selectedMood: String,
    intensity: Float,
    selectedActivities: List<String>,
    notes: String,
    gratitude: String
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Gradiente do botão
    val gradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFFB388FF), Color(0xFFF48FB1))
    )

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .background(brush = gradient, shape = RoundedCornerShape(12.dp))
                .clickable {
                    // Salva a entrada no banco via ViewModel
                    viewModel.salvarEntrada(
                        date = selectedDate,
                        mood = selectedMood,
                        intensity = intensity,
                        activities = selectedActivities,
                        notes = notes,
                        gratitude = gratitude
                    )

                    // Exibe mensagem de confirmação
                    scope.launch {
                        snackbarHostState.showSnackbar("Entrada salva com sucesso ✅")
                    }
                }
                .padding(vertical = 14.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = "Salvar",
                    tint = Color.White,
                    modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Salvar Entrada",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        // Host do Snackbar
        SnackbarHost(hostState = snackbarHostState)
    }
}
