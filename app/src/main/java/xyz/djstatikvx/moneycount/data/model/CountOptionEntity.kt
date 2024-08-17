package xyz.djstatikvx.moneycount.data.model

import kotlinx.serialization.Serializable
import xyz.djstatikvx.moneycount.domain.model.CountOption
import xyz.djstatikvx.moneycount.domain.model.CountOptionValue

@Serializable
data class CountOptionEntity(
    val value: CountOptionValue,
    val selected: Boolean = true
)

fun CountOptionEntity.toDomain() = CountOption(value, selected)