package xyz.djstatikvx.moneycount.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import xyz.djstatikvx.moneycount.data.model.CountOptionEntity
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
        val DEFAULT_OPTIONS = CountOptionValue.entries
            .map { value -> CountOptionEntity(value = value) }
    }

    fun getSelectedOptions(): Flow<List<CountOptionEntity>> {
        return dataStore.data.map { preferences ->
            val jsonString = preferences[KEY_SELECTED_OPTIONS]
                ?: return@map DEFAULT_OPTIONS
            Json.decodeFromString(jsonString)
        }
    }

    suspend fun updateSelectedOptions(options: List<CountOptionEntity>) {
        val jsonString = Json.encodeToString(options)
        context.dataStore.edit { preferences ->
            preferences[KEY_SELECTED_OPTIONS] = jsonString
        }
    }

}