package com.vipulkanade.chaseweatherapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vipulkanade.chaseweatherapp.data.db.dao.CityDao
import com.vipulkanade.chaseweatherapp.data.db.entity.CityEntity

@Database(entities = [CityEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}