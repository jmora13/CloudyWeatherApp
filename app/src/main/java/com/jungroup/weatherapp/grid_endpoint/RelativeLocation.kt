package com.jungroup.weatherapp.grid_endpoint
import com.fasterxml.jackson.annotation.JsonProperty

data class RelativeLocation(
    @JsonProperty("geometry")
    val geometry: GeometryX,
    @JsonProperty("properties")
    val properties: PropertiesX,
    @JsonProperty("type")
    val type: String
)