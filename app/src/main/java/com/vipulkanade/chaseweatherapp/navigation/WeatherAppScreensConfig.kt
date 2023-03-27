package com.vipulkanade.chaseweatherapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vipulkanade.chaseweatherapp.navigation.Destinations
import com.vipulkanade.chaseweatherapp.ui.home.HomeScreen
import com.vipulkanade.chaseweatherapp.ui.home.HomeScreenIntent
import com.vipulkanade.chaseweatherapp.ui.home.HomeScreenViewModel
import com.vipulkanade.chaseweatherapp.ui.home.HomeScreenViewState

@Composable
fun WeatherAppScreensConfig(
    navController: NavHostController,
    homeViewModel: HomeScreenViewModel,
) {
    NavHost(navController = navController, startDestination = Destinations.HOME.route) {
        composable(Destinations.HOME.route) {
            val state = homeViewModel
                .state
                .collectAsState(initial = HomeScreenViewState())
                .value

            HomeScreen(
                state = state,
                onSearchCity = { cityName ->
                    homeViewModel.setSearchQuery(cityName)
                    homeViewModel.processIntents(HomeScreenIntent.SearchCity(cityName))
                },
                onCityClick = { city ->
                    homeViewModel.setLocation(city)
                    homeViewModel.setSearchQuery(city.cityName)
                    homeViewModel.processIntents(HomeScreenIntent.LoadWeatherData)
                }
            )
        }
    }
}