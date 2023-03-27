package com.vipulkanade.chaseweatherapp.core.api

import com.vipulkanade.chaseweatherapp.core.model.Units
import com.vipulkanade.chaseweatherapp.data.remote.GeocodeDto
import com.vipulkanade.chaseweatherapp.data.remote.WeatherResponse
import com.vipulkanade.chaseweatherapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET(Constants.GEOCODE_ENDPOINT)
    suspend fun getGeocodeData(
        @Query("q") cityName: String,
        @Query("limit") limit: Int = 5,
        @Query("appid") appid: String = Constants.APP_ID
    ): Response<List<GeocodeDto>>

    @GET(Constants.WEATHER_ENDPOINT)
    suspend fun getWeatherData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") appid: String = Constants.APP_ID,
        @Query("units") units: String = Units.IMPERIAL.value
    ): Response<WeatherResponse>
}