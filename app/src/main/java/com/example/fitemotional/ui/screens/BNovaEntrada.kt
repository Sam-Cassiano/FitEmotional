import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fitemotional.ui.components.ActivitiesSelector
import com.example.fitemotional.ui.components.DateInputCard
import com.example.fitemotional.ui.components.IntensitySlider
import com.example.fitemotional.ui.components.MoodSelector
import com.example.fitemotional.ui.screens.DiarioHeader
import java.time.LocalDate

@Composable
fun BNovaEntrada(navController: NavController) {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var selectedMood by remember { mutableStateOf("") }
    var intensity by remember { mutableStateOf(5f) }

    // Estado da rolagem
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState) // ❗ torna a tela rolável
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        DiarioHeader()

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Nova Entrada",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = "Como você está se sentindo hoje?",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        DateInputCard(
            initialDate = selectedDate,
            onDateSelected = { newDate ->
                selectedDate = newDate
                println("📅 Data selecionada: $newDate")
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        MoodSelector { mood ->
            selectedMood = mood.label
            println("😄 Humor selecionado: ${mood.label}")
        }

        Spacer(modifier = Modifier.height(16.dp))

        IntensitySlider { value ->
            intensity = value.toFloat()
            println("🔥 Intensidade selecionada: ${value}/10")
        }

        Spacer(modifier = Modifier.height(16.dp))

        ActivitiesSelector { selected ->
            println("✅ Atividades selecionadas: $selected")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "📘 Pronto para registrar sua emoção!",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 18.sp,
                    color = Color.Gray
                )
            )
        }
    }
}
