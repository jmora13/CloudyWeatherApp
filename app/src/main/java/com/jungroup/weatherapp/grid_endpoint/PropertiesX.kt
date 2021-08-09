package com.jungroup.weatherapp.grid_endpoint
import com.fasterxml.jackson.annotation.JsonProperty

data class PropertiesX(
    @JsonProperty("bearing")
    val bearing: Bearing,
    @JsonProperty("city")
    val city: String,
    @JsonProperty("distance")
    val distance: Distance,
    @JsonProperty("state")
    val state: String
)