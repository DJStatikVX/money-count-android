package xyz.djstatikvx.moneycount.ui.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import xyz.djstatikvx.moneycount.R
import xyz.djstatikvx.moneycount.domain.model.CountOption
import xyz.djstatikvx.moneycount.extensions.toMoneyFormat
import xyz.djstatikvx.moneycount.ui.components.AppCard
import xyz.djstatikvx.moneycount.ui.components.AppLoading
import xyz.djstatikvx.moneycount.ui.components.AppText
import xyz.djstatikvx.moneycount.ui.components.AppTextField
import xyz.djstatikvx.moneycount.ui.screens.main.MainScreen.Companion.MAIN_CARDS_SEPARATOR_VALUE
import xyz.djstatikvx.moneycount.ui.screens.settings.SettingsScreen
import xyz.djstatikvx.moneycount.ui.theme.Pink
import java.math.BigDecimal

class MainScreen : Screen {

    companion object {
        // Amount to separate the fields between first and second cards
        const val MAIN_CARDS_SEPARATOR_VALUE = 1
    }

    @Composable
    override fun Content() {
        Scaffold(
            topBar = { MainScreenToolbar() }
        ) {
            MainScreenContent(modifier = Modifier.padding(it))
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScreenToolbar() {
    val navigator = LocalNavigator.current

    TopAppBar(
        title = {
            AppText(
                text = stringResource(R.string.app_name),
                textColor = Color.White,
                fontSize = dimensionResource(id = R.dimen.title_size).value.sp
            )
        },
        actions = {
            IconButton(onClick = { navigator?.push(SettingsScreen()) }) {
                Icon(
                    Icons.Filled.Settings,
                    contentDescription = stringResource(R.string.main_toolbar_settings)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = Pink,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        )
    )
}

@Composable
private fun MainScreenContent(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsState()

    val totalSum = uiState.countOptions.fold(BigDecimal.ZERO) { acc, countOption ->
        acc.add(countOption.multiplier.value.multiply(BigDecimal(countOption.amount)))
    }

    LaunchedEffect(lifecycleState) {
        if (lifecycleState == Lifecycle.State.RESUMED) {
            viewModel.getSelectedCountOptions()
        }
    }

    if (uiState.isLoading) {
        AppLoading()
        return
    }

    Column(
        modifier = modifier.padding(dimensionResource(id = R.dimen.card_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.space_between_cards))
    ) {
        MainScreenTotalSumContainer(
            totalSum = totalSum,
            onClear = { viewModel.clearTotalSum() }
        )
        MainScreenCalculatorContainer(
            countOptions = uiState.countOptions,
            onAmountInputChange = { item, amount ->
                viewModel.updateCountOptionAmount(item.multiplier, amount)
            }
        )
    }
}

@Composable
private fun MainScreenTotalSumContainer(
    totalSum: BigDecimal,
    onClear: () -> Unit
) {
    AppCard {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppTextField(
                value = totalSum.toMoneyFormat(),
                onValueChange = {},
                modifier = Modifier
                    .weight(.9f),
                readOnly = true,
                textColor = Pink
            )
            IconButton(onClick = { onClear() }) {
                Icon(
                    Icons.Filled.Clear,
                    contentDescription = stringResource(R.string.main_clear)
                )
            }
        }
    }
}

@Composable
private fun MainScreenCalculatorContainer(
    countOptions: List<CountOption>,
    onAmountInputChange: (CountOption, String) -> Unit
) {
    val firstCardCountOptions =
        countOptions.filter { it.multiplier.value.toInt() >= MAIN_CARDS_SEPARATOR_VALUE }
    val secondCardCountOptions =
        countOptions.filter { it.multiplier.value.toInt() < MAIN_CARDS_SEPARATOR_VALUE }


    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.space_between_cards))
    ) {
        AppCard {
            firstCardCountOptions.map {
                MainScreenCalculatorItemRow(
                    countOption = it,
                    onValueChange = { amount -> onAmountInputChange(it, amount) }
                )
            }
        }
        AppCard {
            secondCardCountOptions.map {
                MainScreenCalculatorItemRow(
                    countOption = it,
                    onValueChange = { amount -> onAmountInputChange(it, amount) }
                )
            }
        }
    }
}

@Composable
private fun MainScreenCalculatorItemRow(
    countOption: CountOption,
    onValueChange: (String) -> Unit
) {
    val totalSum = countOption.multiplier.value
        .multiply(BigDecimal(countOption.amount))

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.card_padding))
    ) {
        val multiplierSymbol = "x"
        val equalsSymbol = "="

        AppText(
            text = countOption.multiplier.value
                .toMoneyFormat(requireFractionDigits = false),
            modifier = Modifier.weight(.25f),
        )
        AppText(
            text = multiplierSymbol,
            modifier = Modifier.weight(.1f)
        )
        AppTextField(
            value = countOption.amountStr,
            modifier = Modifier.weight(.3f),
            onValueChange = {
                if (it.isEmpty() || it.toIntOrNull() != null) {
                    onValueChange(it)
                }
            }
        )
        AppText(
            text = equalsSymbol,
            modifier = Modifier.weight(.1f)
        )
        AppTextField(
            value = totalSum.toMoneyFormat(),
            onValueChange = {},
            readOnly = true,
            modifier = Modifier.weight(.6f)
        )
    }
}