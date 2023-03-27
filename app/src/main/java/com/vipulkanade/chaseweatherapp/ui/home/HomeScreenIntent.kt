package com.vipulkanade.chaseweatherapp.ui.home

sealed class HomeScreenIntent {
    object LoadWeatherData : HomeScreenIntent()

    data class SearchCity(val cityName: String) : HomeScreenIntent()
}

