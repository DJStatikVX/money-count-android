package xyz.djstatikvx.moneycount.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import xyz.djstatikvx.moneycount.domain.model.CountOptionValue
import xyz.djstatikvx.moneycount.domain.usecase.GetSelectedCountOptionsUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSelectedCountOptionsUseCase: GetSelectedCountOptionsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getSelectedCountOptions()
        }
    }

    private suspend fun getSelectedCountOptions() {
        val selectedCountOptions = withContext(Dispatchers.IO) { getSelectedCountOptionsUseCase() }
        _uiState.update {
            it.copy(
                countOptions = selectedCountOptions,
                isLoading = false
            )
        }
    }

    fun updateCountOptionAmount(value: CountOptionValue, newAmount: String) {
        val newCountOptions = uiState.value.countOptions.map {
            val updatedItem = it.copy(
                amount = newAmount.toIntOrNull() ?: 0,
                amountStr = newAmount
            )
            if (it.multiplier == value) updatedItem else it
        }
        _uiState.update { it.copy(countOptions = newCountOptions) }
    }

    fun clearTotalSum() {
        val newCountOptions = uiState.value.countOptions.map {
            it.copy(
                amount = 0,
                amountStr = "0"
            )
        }
        _uiState.update { it.copy(countOptions = newCountOptions) }
    }
}