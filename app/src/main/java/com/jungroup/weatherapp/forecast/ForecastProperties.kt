package com.jungroup.weatherapp.forecast
import com.fasterxml.jackson.annotation.JsonProperty

data class ForecastProperties(
    @JsonProperty("elevation")
    val elevation: Elevation,
    @JsonProperty("forecastGenerator")
    val forecastGenerator: String,
    @JsonProperty("generatedAt")
    val generatedAt: String,
    @JsonProperty("periods")
    val periods: List<Period>,
    @JsonProperty("units")
    val units: String,
    @JsonProperty("updateTime")
    val updateTime: String,
    @JsonProperty("updated")
    val updated: String,
    @JsonProperty("validTimes")
    val validTimes: String
)