package xyz.djstatikvx.moneycount.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import xyz.djstatikvx.moneycount.R
import xyz.djstatikvx.moneycount.ui.theme.Pink

@Composable
fun AppText(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Bold,
    textColor: Color = Pink,
    fontSize: TextUnit = dimensionResource(id = R.dimen.text_size).value.sp
) {
    Text(
        text = text,
        fontWeight = fontWeight,
        color = textColor,
        modifier = modifier,
        fontSize = fontSize,
        textAlign = TextAlign.Center
    )
}