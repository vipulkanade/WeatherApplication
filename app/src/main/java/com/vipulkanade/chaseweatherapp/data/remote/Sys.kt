package com.vipulkanade.chaseweatherapp.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sys(
    @SerialName("country") val country: String = "",
    @SerialName("id") val id: Int = 0,
    @SerialName("sunrise") val sunrise: Int,
    @SerialName("sunset") val sunset: Int,
    @SerialName("type") val type: Int = 0
)