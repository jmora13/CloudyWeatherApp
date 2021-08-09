package com.jungroup.weatherapp.grid_endpoint
import com.fasterxml.jackson.annotation.JsonProperty

data class Geometry(
    @JsonProperty("coordinates")
    val coordinates: List<Double>,
    @JsonProperty("type")
    val type: String
)