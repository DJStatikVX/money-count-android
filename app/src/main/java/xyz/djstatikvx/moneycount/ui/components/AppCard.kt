package xyz.djstatikvx.moneycount.ui.components

import androidx.compose.material3.Card
import androidx.compose.runtime.Composable

@Composable
fun AppCard(
    content: @Composable () -> Unit
) {
    Card {
        content()
    }
}