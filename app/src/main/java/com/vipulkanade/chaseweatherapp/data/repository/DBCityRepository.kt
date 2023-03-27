package com.vipulkanade.chaseweatherapp.data.repository

import com.vipulkanade.chaseweatherapp.core.model.City
import com.vipulkanade.chaseweatherapp.data.db.dao.CityDao
import com.vipulkanade.chaseweatherapp.data.db.entity.CityEntity

class CityRepository(private val cityDao: CityDao) {
    suspend fun saveCity(city: City) {
        val cityEntity = CityEntity(
            city.name,
            city.cityName,
            city.lat,
            city.lon
        )
        cityDao.saveCity(cityEntity)
    }

    suspend fun getLastSearchedCity(): City? {
        val cityEntity = cityDao.getLastSearchedCity()
        return if (cityEntity != null) {
            City(
                cityEntity.name,
                cityEntity.cityName,
                cityEntity.latitude,
                cityEntity.longitude
            )
        } else {
            null
        }
    }
}