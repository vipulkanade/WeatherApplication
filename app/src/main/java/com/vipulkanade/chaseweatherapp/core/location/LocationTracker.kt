package com.vipulkanade.chaseweatherapp.core.location

import android.location.Location

interface LocationTracker {
    suspend fun getCurrentLocation(): Location?
}