package xyz.djstatikvx.moneycount.ui.screens.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import xyz.djstatikvx.moneycount.domain.usecase.GetSelectedCountOptionsUseCase
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSelectedCountOptionsUseCase: GetSelectedCountOptionsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    suspend fun getSelectedCountOptions() {
        val selectedCountOptions = withContext(Dispatchers.IO) { getSelectedCountOptionsUseCase() }
        _uiState.update { it.copy(countOptions = selectedCountOptions) }
    }

    fun calculateTotalSum() {
        val totalSum = uiState.value.countOptions.fold(BigDecimal.ZERO) { acc, countOption ->
            acc.add(countOption.multiplier.value.multiply(BigDecimal(countOption.amount)))
        }
        _uiState.update { it.copy(totalSum = totalSum) }
    }

    fun clearTotalSum() {
        _uiState.update { it.copy(totalSum = BigDecimal.ZERO) }
    }
}