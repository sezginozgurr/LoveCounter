package com.example.lovecounter.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Date
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DataStoreRepository @Inject constructor(
    private val context: Context
) {
    private object PreferencesKeys {
        val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
        val RELATIONSHIP_START_DATE = longPreferencesKey("relationship_start_date")
    }

    val isOnboardingCompleted: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.ONBOARDING_COMPLETED] ?: false
        }

    suspend fun completeOnboarding() {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.ONBOARDING_COMPLETED] = true
        }
    }

    val relationshipStartDate: Flow<Long?> = context.dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.RELATIONSHIP_START_DATE]
        }

    suspend fun saveRelationshipStartDate(date: Date) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.RELATIONSHIP_START_DATE] = date.time
        }
    }
} 