package xyz.djstatikvx.moneycount.ui.screens.settings

import xyz.djstatikvx.moneycount.domain.model.CountOption

data class SettingsUiState (
    val countOptions: List<CountOption> = emptyList()
)