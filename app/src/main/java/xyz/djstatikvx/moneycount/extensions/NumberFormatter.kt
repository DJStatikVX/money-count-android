package xyz.djstatikvx.moneycount.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

fun BigDecimal.toMoneyFormat(requireFractionDigits: Boolean = true): String {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        if (requireFractionDigits) {
            minimumFractionDigits = 2
        }
        maximumFractionDigits = 2
        isGroupingUsed = false
    }
    return formatter.format(this)
}