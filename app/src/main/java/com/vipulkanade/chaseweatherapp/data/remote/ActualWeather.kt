package com.vipulkanade.chaseweatherapp.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActualWeather(
    @SerialName("description") val description: String,
    @SerialName("icon") val icon: String,
    @SerialName("id") val id: Int,
    @SerialName("main") val main: String
)