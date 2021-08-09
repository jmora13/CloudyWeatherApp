package com.jungroup.weatherapp

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.HiltAndroidApp

val Context.dataStore by preferencesDataStore(name = "settings")
@HiltAndroidApp
class ForecastApplication : Application() {

}