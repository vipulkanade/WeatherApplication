package com.vipulkanade.chaseweatherapp.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.vipulkanade.chaseweatherapp.R
import com.vipulkanade.chaseweatherapp.core.model.City

@Composable
fun HomeScreen(
    state: HomeScreenViewState,
    onSearchCity: (String) -> Unit,
    onCityClick: (City) -> Unit
) {
    val isTextFieldInFocus = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .align(Alignment.TopCenter)
            .fillMaxWidth()) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ClickableTextField(
                    value = state.searchQuery.orEmpty(),
                    onValueChange = { q -> onSearchCity(q) },
                    onClick = { isTextFieldInFocus.value = true },
                    label = { Text("Enter city name") },
                    modifier = Modifier.fillMaxWidth(),
                )

                // Dropdown list of cities
                LaunchedEffect(state.searchQuery) {
                    if (state.searchQuery != null) {
                        isTextFieldInFocus.value = true
                    }
                }
                if (state.searchQuery != null && isTextFieldInFocus.value) {
                    val cities = state.citySuggestions?.cities ?: emptyList()
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(cities) { city ->
                            Text(
                                text = city.name,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        isTextFieldInFocus.value = false
                                        onCityClick(city)
                                    }
                                    .padding(16.dp)
                            )
                        }
                    }
                }
            }
        }


        Box(modifier = Modifier.align(Alignment.Center)) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.Center)
                )
            }

            if (state.error != null) {
                ErrorText(
                    error = state.error,
                    modifier = Modifier.padding(16.dp)
                )
            }

            // Weather data
            if (state.weather != null) {
                val weather = state.weather
                Column {
                    Text(
                        text = weather.name,
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif,
                            fontSize = 32.sp
                        )
                    )
                    Text(
                        text = state.weather.temperature.temp,
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Medium,
                            fontSize = 48.sp
                        )
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(weather.weatherInfo.first().icon)
                                .crossfade(true)
                                .build(),
                            contentDescription = weather.weatherInfo.first().description,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(
                            text = weather.weatherInfo.first().main,
                            style = TextStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp
                            )
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "H: ${weather.temperature.max}",
                            style = TextStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp
                            )
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(
                            text = "L: ${weather.temperature.min}",
                            style = TextStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp
                            )
                        )
                    }
                    Text(
                        text = "Feels Like: ${weather.temperature.feels_like}",
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Medium,
                            fontSize = 24.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun ClickableTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onClick: () -> Unit,
    label: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        modifier = modifier
            .clickable { onClick() }
            .padding(16.dp)
    )
}

@Composable
private fun ErrorText(error: Throwable, modifier: Modifier) {
    Text(
        text = error.message ?: stringResource(id = R.string.home_error_occured),
        textAlign = TextAlign.Center,
        modifier = modifier,
        style = MaterialTheme.typography.body1
    )
}
