package xyz.djstatikvx.moneycount.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import xyz.djstatikvx.moneycount.R
import xyz.djstatikvx.moneycount.domain.model.CountOption
import xyz.djstatikvx.moneycount.extensions.toMoneyFormat
import xyz.djstatikvx.moneycount.ui.components.AppCard
import xyz.djstatikvx.moneycount.ui.components.AppCheckboxWithText
import xyz.djstatikvx.moneycount.ui.components.AppText
import xyz.djstatikvx.moneycount.ui.theme.Pink

class SettingsScreen : Screen {

    companion object {
        private const val SETTINGS_CARDS_SEPARATOR_VALUE = 1
        private const val SETTINGS_MAX_ITEMS_PER_CARD_ROW = 4
    }

    @Composable
    override fun Content() {
        Scaffold(
            topBar = { SettingsScreenToolbar() }
        ) {
            SettingsScreenContent(
                modifier = Modifier.padding(it)
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun SettingsScreenToolbar() {
        val navigator = LocalNavigator.current

        TopAppBar(
            title = {
                AppText(
                    text = stringResource(R.string.settings_toolbar_title),
                    textColor = Color.White,
                    fontSize = dimensionResource(id = R.dimen.title_size).value.sp
                )
            },
            navigationIcon = {
                IconButton(onClick = { navigator?.pop() }) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors().copy(
                containerColor = Pink,
                titleContentColor = Color.White,
                actionIconContentColor = Color.White,
                navigationIconContentColor = Color.White
            )
        )
    }

    @Composable
    private fun SettingsScreenContent(
        modifier: Modifier = Modifier,
        viewModel: SettingsViewModel = hiltViewModel()
    ) {
        val uiState by viewModel.uiState.collectAsState()

        val firstCardCountOptions =
            uiState.countOptions.filter { it.multiplier.value.toInt() >= SETTINGS_CARDS_SEPARATOR_VALUE }
        val secondCardCountOptions =
            uiState.countOptions.filter { it.multiplier.value.toInt() < SETTINGS_CARDS_SEPARATOR_VALUE }

        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = modifier
                    .padding(dimensionResource(id = R.dimen.card_padding)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen.space_between_cards)
                )
            ) {
                SettingsCountOptionsCard(
                    items = firstCardCountOptions,
                    onItemCheckedChange = {
                        /* TODO */
                    }
                )

                SettingsCountOptionsCard(
                    items = secondCardCountOptions,
                    onItemCheckedChange = {
                        /* TODO */
                    }
                )
            }
        }
    }

    @OptIn(ExperimentalLayoutApi::class)
    @Composable
    private fun SettingsCountOptionsCard(
        items: List<CountOption>,
        onItemCheckedChange: (CountOption) -> Unit
    ) {
        AppCard {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen.space_between_items)
                ),
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen.space_between_items)
                ),
                maxItemsInEachRow = SETTINGS_MAX_ITEMS_PER_CARD_ROW
            ) {
                items.map {
                    AppCheckboxWithText(
                        checked = it.selected,
                        onCheckedChange = { /* TODO */ },
                        text = it.multiplier.value.toMoneyFormat(
                            requireFractionDigits = false
                        )
                    )
                }
            }
        }
    }

}