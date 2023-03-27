package com.vipulkanade.chaseweatherapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vipulkanade.chaseweatherapp.data.db.entity.CityEntity

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCity(city: CityEntity)

    @Query("SELECT * FROM cities ORDER BY name ASC LIMIT 1")
    suspend fun getLastSearchedCity(): CityEntity?
}