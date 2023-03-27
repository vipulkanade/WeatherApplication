package com.vipulkanade.chaseweatherapp.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Main(
    @SerialName("feels_like") val feels_like: Double,
    @SerialName("grnd_level") val grnd_level: Int,
    @SerialName("humidity") val humidity: Int,
    @SerialName("pressure") val pressure: Int,
    @SerialName("sea_level") val sea_level: Int,
    @SerialName("temp") val temp: Double,
    @SerialName("temp_max") val tempMax: Double,
    @SerialName("temp_min") val tempMin: Double
)