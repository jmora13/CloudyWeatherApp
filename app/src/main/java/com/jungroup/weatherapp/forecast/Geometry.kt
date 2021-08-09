package com.jungroup.weatherapp.forecast
import com.fasterxml.jackson.annotation.JsonProperty

data class Geometry(
    @JsonProperty("coordinates")
    val coordinates: List<List<List<Double>>>,
    @JsonProperty("type")
    val type: String
)