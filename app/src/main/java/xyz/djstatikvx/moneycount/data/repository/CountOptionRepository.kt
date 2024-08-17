package xyz.djstatikvx.moneycount.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import xyz.djstatikvx.moneycount.data.model.CountOptionEntity
import xyz.djstatikvx.moneycount.domain.model.CountOption
import xyz.djstatikvx.moneycount.domain.model.CountOptionValue
import xyz.djstatikvx.moneycount.extensions.dataStore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountOptionRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val dataStore = context.dataStore

    companion object {
        private val KEY_SELECTED_OPTIONS = stringPreferencesKey("selectedOptions")
        private val DEFAULT_OPTIONS = CountOptionValue.entries
            .map { value -> CountOptionEntity(value = value) }
    }

    suspend fun getSelectedOptions(): List<CountOptionEntity> {
        val preferences = dataStore.data.first()
        val jsonString = preferences[KEY_SELECTED_OPTIONS] ?: return DEFAULT_OPTIONS
        return Json.decodeFromString(jsonString)
    }

    suspend fun updateSelectedOptions(options: List<CountOptionEntity>) {
        val jsonString = Json.encodeToString(options)
        context.dataStore.edit { preferences ->
            preferences[KEY_SELECTED_OPTIONS] = jsonString
        }
    }

}