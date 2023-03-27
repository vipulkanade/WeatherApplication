package com.vipulkanade.chaseweatherapp.core.model

import com.vipulkanade.chaseweatherapp.data.remote.ActualWeather

data class Weather(
    val name: String,
    val temperature: Temperature,
    val weatherInfo: List<WeatherInfo>
)

data class WeatherInfo(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class Temperature(
    val temp: String,
    val feels_like: String,
    val min: String,
    val max: String,
)
