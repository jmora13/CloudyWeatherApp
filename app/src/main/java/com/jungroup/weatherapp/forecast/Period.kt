package com.jungroup.weatherapp.forecast
import com.fasterxml.jackson.annotation.JsonProperty

data class Period(
    @JsonProperty("detailedForecast")
    val detailedForecast: String,
    @JsonProperty("endTime")
    val endTime: String,
    @JsonProperty("icon")
    val icon: String,
    @JsonProperty("isDaytime")
    val isDaytime: Boolean,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("number")
    val number: Int,
    @JsonProperty("shortForecast")
    val shortForecast: String,
    @JsonProperty("startTime")
    val startTime: String,
    @JsonProperty("temperature")
    val temperature: Int,
    @JsonProperty("temperatureTrend")
    val temperatureTrend: Any?,
    @JsonProperty("temperatureUnit")
    val temperatureUnit: String,
    @JsonProperty("windDirection")
    val windDirection: String,
    @JsonProperty("windSpeed")
    val windSpeed: String
)