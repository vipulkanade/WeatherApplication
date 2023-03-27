package com.vipulkanade.chaseweatherapp

import com.vipulkanade.chaseweatherapp.core.model.Geocode
import com.vipulkanade.chaseweatherapp.core.model.Temperature
import com.vipulkanade.chaseweatherapp.core.model.Weather
import com.vipulkanade.chaseweatherapp.data.remote.Clouds
import com.vipulkanade.chaseweatherapp.data.remote.Coord
import com.vipulkanade.chaseweatherapp.data.remote.Main
import com.vipulkanade.chaseweatherapp.data.remote.Sys
import com.vipulkanade.chaseweatherapp.data.remote.WeatherResponse
import com.vipulkanade.chaseweatherapp.data.remote.Wind

val mockSuccessWeatherResponse = Weather(
    name = "London",
    temperature = Temperature(
        temp = "3°C",
        feels_like = "3°C",
        min = "1°C",
        max = "3°C",
    ),
    weatherInfo = listOf()
)

val mockSuccessGeocodeResponse = Geocode(
    cities = listOf()
)

val mockSuccessOpenWeatherResponse = WeatherResponse(
    base = "",
    clouds = Clouds(
        all = 0
    ),
    cod = 0,
    coord = Coord(
        lat = 0.0,
        lon = 0.0
    ),
    dt = 0,
    id = 0,
    main = Main(
        feels_like = 0.0,
        temp = 0.0,
        tempMax = 0.0,
        tempMin = 0.0,
        grnd_level = 0,
        humidity = 0,
        pressure = 0,
        sea_level = 0,
    ),
    name= "",
    sys = Sys(
        country = "",
        id = 0,
        sunrise = 0,
        sunset = 0,
    ),
    timezone = 0,
    visibility = 0,
    weather = listOf(),
    wind = Wind(
        deg = 0,
        gust = 0.0,
        speed = 0.0
    )
)