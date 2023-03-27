package com.vipulkanade.chaseweatherapp.data.mapper

import com.vipulkanade.chaseweatherapp.core.model.Temperature
import com.vipulkanade.chaseweatherapp.core.model.Units
import com.vipulkanade.chaseweatherapp.core.model.Weather
import com.vipulkanade.chaseweatherapp.core.model.WeatherInfo
import com.vipulkanade.chaseweatherapp.data.remote.ActualWeather
import com.vipulkanade.chaseweatherapp.data.remote.Main
import com.vipulkanade.chaseweatherapp.data.remote.WeatherResponse
import com.vipulkanade.chaseweatherapp.utils.Constants
import kotlin.math.roundToInt

fun WeatherResponse.toCoreModel(unit: String): Weather = Weather(
    name = name,
    temperature = main.toCoreModel(unit),
    weatherInfo = weather.map { it.toCoreModel(unit) }
)

fun Main.toCoreModel(unit: String): Temperature = Temperature(
    temp = formatTemperatureValue(temp, unit),
    feels_like = formatTemperatureValue(feels_like, unit),
    max = formatTemperatureValue(tempMax, unit),
    min = formatTemperatureValue(tempMin, unit),
)

fun ActualWeather.toCoreModel(unit: String): WeatherInfo = WeatherInfo(
    id = id,
    main = main,
    description = description,
    icon = "${Constants.ICON_BASE_URL}$icon@2x.png"
)

private fun formatTemperatureValue(temperature: Double, unit: String): String =
    "${temperature.roundToInt()}${getUnitSymbols(unit = unit)}"

private fun getUnitSymbols(unit: String) = when (unit) {
    Units.METRIC.value -> Units.METRIC.tempLabel
    Units.IMPERIAL.value -> Units.IMPERIAL.tempLabel
    Units.STANDARD.value -> Units.STANDARD.tempLabel
    else -> "N/A"
}