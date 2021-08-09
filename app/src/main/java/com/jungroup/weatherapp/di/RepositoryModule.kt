package com.jungroup.weatherapp.di

import com.jungroup.weatherapp.api.ForecastRepository
import com.jungroup.weatherapp.api.ForecastService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    //REPOSITORY SINGLETON
    @Provides
    @ViewModelScoped
    fun providesRepo(forecastService: ForecastService): ForecastRepository {
        return ForecastRepository(forecastService)
    }
}