package com.arunasbedzinskas.movio.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataStore @Inject constructor(private val appContext: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(NAME)

    companion object {

        private const val NAME = "MovioDataStore"
    }
}
