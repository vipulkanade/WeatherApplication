package com.vipulkanade.chaseweatherapp.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Geocodes(
    val geocodes: List<GeocodeDto>
)

@Serializable
data class GeocodeDto(
    @SerialName("name") val name: String,
    @SerialName("local_names")  val local_names: LocalNames?,
    @SerialName("lat") val lat: Double,
    @SerialName("lon") val lon: Double,
    @SerialName("country") val country: String?,
    @SerialName("state") val state: String?,
)