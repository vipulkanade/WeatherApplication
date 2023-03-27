package com.vipulkanade.chaseweatherapp.core.api.repository

import com.vipulkanade.chaseweatherapp.core.model.Geocode
import com.vipulkanade.chaseweatherapp.core.model.Location
import com.vipulkanade.chaseweatherapp.core.model.Units
import com.vipulkanade.chaseweatherapp.core.model.Weather
import kotlinx.coroutines.flow.Flow

interface OpenWeatherRepository {
    fun fetchWeatherData(
        location: Location,
        units: Units = Units.IMPERIAL
    ) : Flow<Result<Weather>>

    fun fetchGeocodeData(
        cityName: String,
        limit: Int
    ) : Flow<Result<Geocode>>
}