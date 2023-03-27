package com.vipulkanade.chaseweatherapp.data.mapper

import com.vipulkanade.chaseweatherapp.core.model.City
import com.vipulkanade.chaseweatherapp.core.model.Geocode
import com.vipulkanade.chaseweatherapp.data.remote.GeocodeDto
import com.vipulkanade.chaseweatherapp.data.remote.Geocodes

fun Geocodes.toCoreModel(): Geocode = Geocode(
    cities = geocodes.map { it.toCoreModel() }
)

fun GeocodeDto.toCoreModel(): City = City(
    name = buildString {
        append(name)
        if (state != null)
            append(", $state")
        if (country != null)
            append(", $country")
    },
    cityName = name,
    lat = lat,
    lon = lon
)

fun List<GeocodeDto>.toCoreModel(): Geocode = Geocode(
    cities = map { it.toCoreModel() }
)