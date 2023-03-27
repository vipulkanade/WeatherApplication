package com.vipulkanade.chaseweatherapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vipulkanade.chaseweatherapp.core.location.GetLocationUseCase
import com.vipulkanade.chaseweatherapp.core.model.City
import com.vipulkanade.chaseweatherapp.core.model.Geocode
import com.vipulkanade.chaseweatherapp.core.model.Location
import com.vipulkanade.chaseweatherapp.core.model.Units
import com.vipulkanade.chaseweatherapp.core.model.Weather
import com.vipulkanade.chaseweatherapp.data.repository.CityRepository
import com.vipulkanade.chaseweatherapp.data.repository.OpenWeatherRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val weatherRepositoryImpl: OpenWeatherRepositoryImpl,
    private val cityRepository: CityRepository,
    private val getCurrentLocation: GetLocationUseCase
) :ViewModel() {

    private val _state = MutableStateFlow(
        HomeScreenViewState(
            isLoading = true,
            citySuggestions = null
        )
    )
    val state: StateFlow<HomeScreenViewState> = _state.asStateFlow()

    init {
        // Load the last searched city upon app launch
        viewModelScope.launch {
            cityRepository.getLastSearchedCity()?.let { city ->
                setLocation(city = city)
            }
        }
    }

    fun processIntents(homeScreenIntent: HomeScreenIntent) {
        when (homeScreenIntent) {
            is HomeScreenIntent.LoadWeatherData -> {
                viewModelScope.launch {
                    var location: Location? = null

                    // First check if the user entered a search query
                    if (!state.value.searchQuery.isNullOrEmpty()) {
                        val geocodeResult = weatherRepositoryImpl.fetchGeocodeData(
                            cityName = state.value.searchQuery.orEmpty(),
                            limit = 1
                        ).firstOrNull()

                        if (geocodeResult != null && geocodeResult.isSuccess) {
                            val geocodeData = geocodeResult.getOrThrow()
                            val city = geocodeData.cities.firstOrNull()
                            if (city != null) {
                                location = Location(city.lat, city.lon)
                            }
                        }
                    }

                    // If no search query or geocode result, use current location
                    if (location == null) {
                        val locationData = getCurrentLocation.getLocation()
                        location = if (locationData != null) {
                            Location(locationData.latitude, locationData.longitude)
                        } else {
                            val city = cityRepository.getLastSearchedCity()
                            if (city != null) {
                                Location(city.lat, city.lon)
                            } else {
                                state.value.defaultLocation
                            }
                        }
                    }

                    weatherRepositoryImpl.fetchWeatherData(
                        location = location,
                        Units.IMPERIAL
                    ).collect {
                        processResult(it)
                    }
                }
            }
            is HomeScreenIntent.SearchCity -> {
                viewModelScope.launch {
                    weatherRepositoryImpl.fetchGeocodeData(
                        cityName = homeScreenIntent.cityName,
                        limit = 5
                    ).collect {
                        processGeocodeResult(it)
                    }
                }
            }
        }
    }

    private fun processResult(result: Result<Weather>) {
        when {
            result.isSuccess -> {
                val weatherData = result.getOrThrow()
                setState {
                    copy(
                        weather = weatherData,
                        isLoading = false,
                        error = null
                    )
                }
            }

            result.isFailure -> {
                setState {
                    copy(
                        isLoading = false,
                        error = result.exceptionOrNull()
                            ?: Throwable("Unknown error occurred")
                    )
                }
            }
        }
    }

    private fun processGeocodeResult(result: Result<Geocode>) {
        when {
            result.isSuccess -> {
                val geocodeData = result.getOrThrow()
                setState {
                    copy(
                        isLoading = false,
                        error = null,
                        citySuggestions = geocodeData
                    )
                }
            }

            result.isFailure -> {
                setState {
                    copy(
                        isLoading = false,
                        error = result.exceptionOrNull()
                            ?: Throwable("Unknown error occurred"),
                        citySuggestions = null
                    )
                }
            }
        }
    }

    fun setSearchQuery(query: String) {
        setState {
            copy(
                searchQuery = query
            )
        }
    }

    fun setLocation(city: City) {
        viewModelScope.launch {
            cityRepository.saveCity(
                City(
                    name = city.name,
                    cityName = city.cityName,
                    lat = city.lat,
                    lon = city.lon
                )
            )
        }

        setState {
            copy(
                defaultLocation = Location(city.lat, city.lon)
            )
        }
    }


    private fun setState(stateReducer: HomeScreenViewState.() -> HomeScreenViewState) {
        viewModelScope.launch {
            _state.emit(stateReducer(state.value))
        }
    }
}