package com.vipulkanade.chaseweatherapp.data.repository

import com.vipulkanade.chaseweatherapp.core.api.OpenWeatherApi
import com.vipulkanade.chaseweatherapp.core.api.repository.OpenWeatherRepository
import com.vipulkanade.chaseweatherapp.core.model.Geocode
import com.vipulkanade.chaseweatherapp.core.model.Location
import com.vipulkanade.chaseweatherapp.core.model.Units
import com.vipulkanade.chaseweatherapp.core.model.Weather
import com.vipulkanade.chaseweatherapp.data.mapper.toCoreModel
import com.vipulkanade.chaseweatherapp.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OpenWeatherRepositoryImpl @Inject constructor(
    private val api: OpenWeatherApi
) : OpenWeatherRepository{
    override fun fetchWeatherData(
        location: Location,
        units: Units
    ): Flow<Result<Weather>> = flow {
        val response = api.getWeatherData(
            longitude = location.longitude,
            latitude = location.latitude,
            appid = Constants.APP_ID
        )

        if (response.isSuccessful && response.body() != null) {
            val weatherData = response.body()!!.toCoreModel(units.value)
            emit(Result.success(weatherData))
        } else {
            emit(Result.failure(Throwable("Error Occurred : ${response.errorBody()?.string()}")))
        }
    }

    override fun fetchGeocodeData(
        cityName: String,
        limit: Int
    ): Flow<Result<Geocode>> = flow {
        val response = api.getGeocodeData(
            cityName = cityName,
            appid = Constants.APP_ID
        )

        if (response.isSuccessful && response.body() != null) {
            val geocodesData = response.body()!!.toCoreModel()
            emit(Result.success(geocodesData))
        } else {
            emit(Result.failure(Throwable("Error Occurred : ${response.errorBody()?.string()}")))
        }
    }
}