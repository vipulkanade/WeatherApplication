package com.vipulkanade.chaseweatherapp.di

import android.content.Context
import androidx.room.Room
import com.vipulkanade.chaseweatherapp.data.db.AppDatabase
import com.vipulkanade.chaseweatherapp.data.db.dao.CityDao
import com.vipulkanade.chaseweatherapp.data.repository.CityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    fun provideCityDao(database: AppDatabase): CityDao {
        return database.cityDao()
    }

    @Provides
    fun provideCityRepository(cityDao: CityDao): CityRepository {
        return CityRepository(cityDao)
    }
}