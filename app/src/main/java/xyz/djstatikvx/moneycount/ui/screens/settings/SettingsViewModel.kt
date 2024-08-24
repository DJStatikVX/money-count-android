package xyz.djstatikvx.moneycount.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import xyz.djstatikvx.moneycount.domain.usecase.GetCountOptionsUseCase
import xyz.djstatikvx.moneycount.domain.usecase.UpdateSelectedCountOptionsUseCase
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getCountOptionsUseCase: GetCountOptionsUseCase,
    private val updateSelectedCountOptionsUseCase: UpdateSelectedCountOptionsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getCountOptions()
        }
    }

    private suspend fun getCountOptions() {
        val countOptions = withContext(Dispatchers.IO) {
            getCountOptionsUseCase()
                .first()
        }
        _uiState.update { it.copy(countOptions = countOptions) }
    }

    fun updateCountOptions() {
        viewModelScope.launch(Dispatchers.IO) {
            updateSelectedCountOptionsUseCase(uiState.value.countOptions)
        }
    }
}