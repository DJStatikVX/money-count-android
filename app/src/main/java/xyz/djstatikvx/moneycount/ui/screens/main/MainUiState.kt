package xyz.djstatikvx.moneycount.ui.screens.main

import xyz.djstatikvx.moneycount.domain.model.CountOption
import java.math.BigDecimal

data class MainUiState(
    val isLoading: Boolean = true,
    val countOptions: List<CountOption> = emptyList()
)