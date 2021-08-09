package com.jungroup.weatherapp

import android.content.Context
import androidx.lifecycle.ViewModel
import com.jungroup.weatherapp.api.ForecastRepository
import com.jungroup.weatherapp.forecast.ForecastModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(private val repository: ForecastRepository): ViewModel() {

    suspend fun getForecastResponse(context: Context): ForecastModel? {
        //SENDS CONTEXT TO REPOSITORY TO GET FORECAST RESPONSE
        return repository.getForecastResponse(context)
    }

}