package xyz.djstatikvx.moneycount.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import xyz.djstatikvx.moneycount.R
import xyz.djstatikvx.moneycount.extensions.toMoneyFormat
import xyz.djstatikvx.moneycount.ui.components.AppCard
import xyz.djstatikvx.moneycount.ui.components.AppTextField
import xyz.djstatikvx.moneycount.ui.screens.settings.SettingsScreen
import xyz.djstatikvx.moneycount.ui.theme.Pink
import java.math.BigDecimal

class MainScreen : Screen {

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
            Text(
                text = stringResource(R.string.app_name),
                fontWeight = FontWeight.SemiBold
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

    Column(
        modifier.padding(dimensionResource(id = R.dimen.card_padding))
    ) {
        MainScreenTotalSumContainer(
            totalSum = uiState.totalSum,
            onClear = { viewModel.clearTotalSum() }
        )
        MainScreenCalculatorContainer()
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
                readOnly = true,
                modifier = Modifier
                    .weight(.9f)
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
private fun MainScreenCalculatorContainer() {
    LazyColumn {

    }
}