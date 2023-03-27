package com.vipulkanade.chaseweatherapp.utils

object Constants {

    // URL
    public final const val BASE_URL = "https://api.openweathermap.org/"
    public final const val ICON_BASE_URL = "https://openweathermap.org/img/wn/"

    //Endpoint
    public final const val GEOCODE_ENDPOINT = "geo/1.0/direct"
    public final const val WEATHER_ENDPOINT = "data/2.5/weather"

    // AppID
    public final const val APP_ID = "APP_ID_HERE"
}

object ExceptionTitles {
    const val GPS_DISABLED = "GPS Disabled"
    const val NO_PERMISSION = "No Permission"
}
