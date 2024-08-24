package xyz.djstatikvx.moneycount.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import dagger.hilt.android.AndroidEntryPoint
import xyz.djstatikvx.moneycount.ui.screens.main.MainScreen
import xyz.djstatikvx.moneycount.ui.screens.main.MainViewModel
import xyz.djstatikvx.moneycount.ui.theme.MoneyCountTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition {
            viewModel.uiState.value.isLoading
        }

        enableEdgeToEdge()
        setContent {
            MoneyCountTheme(darkTheme = false) {
                Navigator(MainScreen()) { navigator ->
                    SlideTransition(navigator)
                }
            }
        }
    }
}