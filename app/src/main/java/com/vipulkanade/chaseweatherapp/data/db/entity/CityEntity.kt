package com.vipulkanade.chaseweatherapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class CityEntity(
    @PrimaryKey val name: String,
    val cityName: String,
    val latitude: Double,
    val longitude: Double
)
