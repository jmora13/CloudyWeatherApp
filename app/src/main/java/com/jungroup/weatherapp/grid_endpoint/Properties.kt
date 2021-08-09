package com.jungroup.weatherapp.grid_endpoint
import com.fasterxml.jackson.annotation.JsonProperty

data class Properties(
    @JsonProperty("county")
    val county: String,
    @JsonProperty("cwa")
    val cwa: String,
    @JsonProperty("fireWeatherZone")
    val fireWeatherZone: String,
    @JsonProperty("forecast")
    val forecast: String,
    @JsonProperty("forecastGridData")
    val forecastGridData: String,
    @JsonProperty("forecastHourly")
    val forecastHourly: String,
    @JsonProperty("forecastOffice")
    val forecastOffice: String,
    @JsonProperty("forecastZone")
    val forecastZone: String,
    @JsonProperty("gridId")
    val gridId: String,
    @JsonProperty("gridX")
    val gridX: Int,
    @JsonProperty("gridY")
    val gridY: Int,
    @JsonProperty("@id")
    val id: String,
    @JsonProperty("observationStations")
    val observationStations: String,
    @JsonProperty("radarStation")
    val radarStation: String,
    @JsonProperty("relativeLocation")
    val relativeLocation: RelativeLocation,
    @JsonProperty("timeZone")
    val timeZone: String,
    @JsonProperty("@type")
    val type: String
)