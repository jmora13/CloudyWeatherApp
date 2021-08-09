package com.jungroup.weatherapp.grid_endpoint
import com.fasterxml.jackson.annotation.JsonProperty

data class Distance(
    @JsonProperty("unitCode")
    val unitCode: String,
    @JsonProperty("value")
    val value: Double
)