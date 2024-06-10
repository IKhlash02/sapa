package com.example.sapa.data.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user")

class UserPreference private constructor(private val dataStore: DataStore<Preferences>) {

    suspend fun saveDataUser(user: UserModel) {
        dataStore.edit { preferences ->
            preferences[NAME_KEY] = user.name
            preferences[LEVEL_KEY] = user.level
            preferences[POINT_KEY] = user.point
            preferences[HEART_KEY] = user.heart
            preferences[COMPLETED_KEY] = user.completed
        }
    }

    suspend fun updateUserName(name: String) {
        dataStore.edit { preferences ->
            preferences[NAME_KEY] = name
        }
    }

    suspend fun updateUserLevel(level: Int) {
        dataStore.edit { preferences ->
            preferences[LEVEL_KEY] = level
        }
    }

    suspend fun updateUserPoint(point: Int) {
        dataStore.edit { preferences ->
            preferences[POINT_KEY] = point
        }
    }

    suspend fun updateUserHeart(heart: Int) {
        dataStore.edit { preferences ->
            preferences[HEART_KEY] = heart
        }
    }

    suspend fun updateUserCompleted(completed: Int) {
        dataStore.edit { preferences ->
            preferences[COMPLETED_KEY] = completed
        }
    }




    fun getUserData(): Flow<UserModel> {
        return dataStore.data.map { preferences ->
            UserModel(
                name = preferences[NAME_KEY] ?: "",
                level = preferences[LEVEL_KEY] ?: 0,
                point = preferences[POINT_KEY] ?: 0,
                heart = preferences[HEART_KEY] ?: 5,
                completed = preferences[COMPLETED_KEY] ?: 1,
            )
        }
    }

    suspend fun clear() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null

        private val NAME_KEY = stringPreferencesKey("name")
        private val POINT_KEY = intPreferencesKey("point")
        private val LEVEL_KEY = intPreferencesKey("level")
        private val HEART_KEY = intPreferencesKey("heart")
        private val COMPLETED_KEY = intPreferencesKey("completed")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}