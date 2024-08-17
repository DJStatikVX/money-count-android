package xyz.djstatikvx.moneycount.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import xyz.djstatikvx.moneycount.R

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    textColor: Color = Color.Black,
    keyboardType: KeyboardType = KeyboardType.Number
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        readOnly = readOnly,
        modifier = modifier,
        textStyle = TextStyle(
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = dimensionResource(id = R.dimen.text_size).value.sp
        ),
        colors = TextFieldDefaults.colors().copy(
            focusedTextColor = textColor,
            unfocusedTextColor = textColor
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType
        )
    )
}