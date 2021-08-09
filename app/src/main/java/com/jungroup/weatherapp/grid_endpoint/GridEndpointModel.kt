package com.jungroup.weatherapp.grid_endpoint
import com.fasterxml.jackson.annotation.JsonProperty

data class GridEndpointModel(
    @JsonProperty("@context")
    val context: List<Any>,
    @JsonProperty("geometry")
    val geometry: Geometry,
    @JsonProperty("id")
    val id: String,
    @JsonProperty("properties")
    val properties: Properties,
    @JsonProperty("type")
    val type: String
)