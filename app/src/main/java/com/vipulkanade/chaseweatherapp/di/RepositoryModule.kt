package com.vipulkanade.chaseweatherapp.di

import com.vipulkanade.chaseweatherapp.core.api.repository.OpenWeatherRepository
import com.vipulkanade.chaseweatherapp.data.repository.OpenWeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun bindOpenWeatherRepository(openWeatherRepository: OpenWeatherRepositoryImpl): OpenWeatherRepository
}