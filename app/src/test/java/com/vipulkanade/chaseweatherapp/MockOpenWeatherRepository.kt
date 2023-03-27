package com.vipulkanade.chaseweatherapp

import com.vipulkanade.chaseweatherapp.core.api.repository.OpenWeatherRepository
import com.vipulkanade.chaseweatherapp.core.model.Geocode
import com.vipulkanade.chaseweatherapp.core.model.Location
import com.vipulkanade.chaseweatherapp.core.model.Units
import com.vipulkanade.chaseweatherapp.core.model.Weather
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockOpenWeatherRepository : OpenWeatherRepository {

    var isSuccessful = true

    override fun fetchWeatherData(location: Location, units: Units): Flow<Result<Weather>> = flow {
        if (isSuccessful){
            emit(Result.success(mockSuccessWeatherResponse))
        }else{
            emit(Result.failure(Throwable("An Error Occurred")))
        }
    }

    override fun fetchGeocodeData(cityName: String, limit: Int): Flow<Result<Geocode>> = flow {
        if (isSuccessful){
            emit(Result.success(mockSuccessGeocodeResponse))
        }else{
            emit(Result.failure(Throwable("An Error Occurred")))
        }
    }
}