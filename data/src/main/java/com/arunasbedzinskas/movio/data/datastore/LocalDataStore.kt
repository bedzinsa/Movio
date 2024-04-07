package com.arunasbedzinskas.movio.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.arunasbedzinskas.movio.models.data.UserData
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataStore @Inject constructor(
    private val appContext: Context,
    private val gson: Gson
) {

    suspend fun setUser(userData: UserData) {
        appContext.dataStore.edit { prefs ->
            prefs[userDataKey] = gson.toJson(userData)
        }
    }

    fun getUser(): Flow<UserData?> = appContext.dataStore
        .data
        .map { gson.fromJson(it[userDataKey], UserData::class.java) }

    suspend fun addFavorite(id: Long) {
        appContext.dataStore.edit { prefs ->
            val favoriteIds = prefs[favoritesKey]?.toMutableSet() ?: mutableSetOf()
            favoriteIds.add(id.toString())
            prefs[favoritesKey] = favoriteIds
        }
    }

    suspend fun removeFavorite(id: Long) {
        appContext.dataStore.edit { prefs ->
            val favoriteIds = prefs[favoritesKey]?.toMutableSet() ?: return@edit
            favoriteIds.remove(id.toString())
            prefs[favoritesKey] = favoriteIds
        }
    }

    fun getFavorites(): Flow<List<Long>> = appContext.dataStore.data.map { prefs ->
        prefs[favoritesKey]?.map { it.toLong() } ?: listOf()
    }

    companion object {

        private const val NAME = "MovioDataStore"
        private const val KEY_USER_DATA = "UserData"
        private const val KEY_FAVORITES = "Favorites"

        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(NAME)
        private val userDataKey = stringPreferencesKey(KEY_USER_DATA)
        private val favoritesKey = stringSetPreferencesKey(KEY_FAVORITES)
    }
}
