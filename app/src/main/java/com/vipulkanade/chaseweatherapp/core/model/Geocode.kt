package com.vipulkanade.chaseweatherapp.core.model

data class Geocode(
    val cities: List<City>
)

data class City(
    val name: String,
    val cityName: String,
    val lat: Double,
    val lon: Double
)
