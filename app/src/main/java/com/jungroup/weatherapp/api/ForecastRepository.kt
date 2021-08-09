package com.jungroup.weatherapp.api


import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.jungroup.weatherapp.dataStore
import com.jungroup.weatherapp.forecast.ForecastModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ForecastRepository @Inject constructor(private val forecastService: ForecastService) {
    private lateinit var forecastLink: String
    private lateinit var city: String
    suspend fun getForecastResponse(context: Context): ForecastModel?{
        //READ DATASTORE OBJECT AND GET SAVED COORDINATES AS STRING
        val dataStoreKey = stringPreferencesKey("coordinates")
        var coordinates = context.dataStore.data.first()

        return try {
            //IF LOCATION FOUND, DATASTORE WILL HAVE AN ENTRY SAVED AND NOT BE EMPTY
            if(coordinates.asMap().isNotEmpty()) {
                //FIRST API CALL TO GET FORECAST LINK, LOCATION DEFAULTS TO NEW YORK IF LOCATION NULL
                forecastLink = forecastService.getGrid(coordinates[dataStoreKey] ?: "40.730610,-73.935242").body()!!.properties.forecast
                //SAVES CITY NAME TO CHANGE TITLE BAR LATER
                city = forecastService.getGrid(coordinates[dataStoreKey] ?: "40.730610,-73.935242").body()!!.properties.relativeLocation.properties.city
            } else {
                //ELSE MAKE NETWORK CALL FOR NEW YORK NEW YORK
                forecastLink = forecastService.getGrid("40.730610,-73.935242").body()!!.properties.forecast
                city = forecastService.getGrid( "40.730610,-73.935242").body()!!.properties.relativeLocation.properties.city
            }
            val dataStoreKey = stringPreferencesKey("city")
            context?.dataStore?.edit { settings ->
                settings[dataStoreKey] = city
            }
            //GETS FORECAST USING PREVIOUSLY FETCHED URL
            forecastService.getForecast(forecastLink!!).body()!!
        } catch (e: IOException) {
            Log.d("IOEXCEPTION", e.message.toString())
            null
        }
    }

}