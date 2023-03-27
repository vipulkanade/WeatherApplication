package com.vipulkanade.chaseweatherapp.ui.home

import android.provider.ContactsContract.Contacts.AggregationSuggestions
import com.vipulkanade.chaseweatherapp.core.model.Geocode
import com.vipulkanade.chaseweatherapp.core.model.Location
import com.vipulkanade.chaseweatherapp.core.model.Weather

data class HomeScreenViewState(
    val units: String = "",
    val defaultLocation: Location = Location(0.0, 0.0),
    val locationName: String = "-",
    val weather: Weather? = null,
    val citySuggestions: Geocode? = null,
    val searchQuery: String? = null,
    val isLoading: Boolean = false,
    val error: Throwable? = null,
)