package com.jungroup.weatherapp.di

import com.jungroup.weatherapp.api.ForecastService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.Executors
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    //RETROFIT INSTANCE SINGLETON
    @Provides
    @Singleton
    fun provideForecastService(): ForecastService {
        return Retrofit.Builder()
            .baseUrl("https://api.weather.gov/")
            .callbackExecutor(Executors.newSingleThreadExecutor())
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
            .create(ForecastService::class.java)
    }
}