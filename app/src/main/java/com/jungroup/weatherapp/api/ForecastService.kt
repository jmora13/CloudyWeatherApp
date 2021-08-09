package com.jungroup.weatherapp.api


import com.jungroup.weatherapp.forecast.ForecastModel
import com.jungroup.weatherapp.grid_endpoint.GridEndpointModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface ForecastService {
    //API TO GET FORECAST URL
    @GET("points/{coordinates}")
    suspend fun getGrid(
        @Path("coordinates") coordinates: String
    ): Response<GridEndpointModel>

    //GETS FORECAST USING PREVIOUSLY FETCHED URL
    @GET
    suspend fun getForecast(@Url url: String): Response<ForecastModel>
}