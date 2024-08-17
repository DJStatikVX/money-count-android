package xyz.djstatikvx.moneycount.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import xyz.djstatikvx.moneycount.ui.theme.Pink

@Composable
fun AppText(
    text: String,
    fontWeight: FontWeight = FontWeight.Bold,
    textColor: Color = Pink
) {
    Text(
        text = text,
        fontWeight = fontWeight,
        color = textColor
    )
}