package xyz.djstatikvx.moneycount.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.selection.toggleable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.sp
import xyz.djstatikvx.moneycount.R

@Composable
fun AppCheckboxWithText(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    text: String
) {
    Row(
        modifier = Modifier.toggleable(
            value = checked,
            onValueChange = onCheckedChange,
            role = Role.Checkbox
        ),
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.space_between_items)
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppCheckbox(checked = checked)
        AppText(text, fontSize = dimensionResource(id = R.dimen.checkbox_text_size).value.sp)
    }
}