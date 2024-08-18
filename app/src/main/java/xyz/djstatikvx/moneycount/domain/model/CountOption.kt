package xyz.djstatikvx.moneycount.domain.model

import kotlinx.serialization.Serializable
import xyz.djstatikvx.moneycount.data.model.CountOptionEntity

@Serializable
data class CountOption(
    val multiplier: CountOptionValue,
    val selected: Boolean = true,
    val amount: Int = 0,
    val amountStr: String = amount.toString()
)

fun CountOption.toEntity() = CountOptionEntity(multiplier, selected)
