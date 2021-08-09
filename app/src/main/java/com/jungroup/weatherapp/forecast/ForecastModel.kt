package com.jungroup.weatherapp.forecast
import com.fasterxml.jackson.annotation.JsonProperty

data class ForecastModel(
    @JsonProperty("@context")
    val context: List<Any>,
    @JsonProperty("geometry")
    val geometry: Geometry,
    @JsonProperty("properties")
    val forecastProperties: ForecastProperties,
    @JsonProperty("type")
    val type: String
)