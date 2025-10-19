package com.example.fitemotional.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fitemotional.R

@Composable
fun DiarioHeader(
    title: String = "Meu Diário",
    subtitle: String = "Como você está hoje?",
    iconRes: Int = R.drawable.ic_diario,
    iconTint: Color = Color.Magenta
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp)
    ) {
        // Ícone do diário
        Surface(
            modifier = Modifier.size(52.dp),
            color = Color.Transparent,
            shape = MaterialTheme.shapes.medium
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = "Ícone Diário",
                tint = iconTint,
                modifier = Modifier.size(40.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Título e subtítulo
        Column {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    brush = Brush.linearGradient(
                        listOf(Color(0xFF9C27B0), Color(0xFFE91E63))
                    ),
                    shadow = Shadow(
                        color = Color.Black.copy(alpha = 0.2f),
                        blurRadius = 4f
                    )
                )
            )
            Text(
                text = subtitle,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}
