package com.jungroup.weatherapp.forecast
import com.fasterxml.jackson.annotation.JsonProperty

data class Elevation(
    @JsonProperty("unitCode")
    val unitCode: String,
    @JsonProperty("value")
    val value: Double
)