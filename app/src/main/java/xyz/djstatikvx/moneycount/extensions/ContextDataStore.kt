package xyz.djstatikvx.moneycount.extensions

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore by preferencesDataStore("app_preferences")