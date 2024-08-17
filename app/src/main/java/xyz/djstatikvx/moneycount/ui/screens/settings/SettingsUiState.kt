package xyz.djstatikvx.moneycount.ui.screens.settings

import xyz.djstatikvx.moneycount.domain.model.CountOption

data class SettingsUiState (
    val selectedOptions: List<CountOption> = mutableListOf()
)