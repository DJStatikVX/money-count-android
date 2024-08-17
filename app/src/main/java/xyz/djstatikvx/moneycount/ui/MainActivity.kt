package xyz.djstatikvx.moneycount.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import dagger.hilt.android.AndroidEntryPoint
import xyz.djstatikvx.moneycount.ui.screens.main.MainScreen
import xyz.djstatikvx.moneycount.ui.theme.MoneyCountTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoneyCountTheme {
                Navigator(MainScreen()) { navigator ->
                    SlideTransition(navigator)
                }
            }
        }
    }
}