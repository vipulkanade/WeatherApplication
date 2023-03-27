package com.vipulkanade.chaseweatherapp

import app.cash.turbine.test
import com.vipulkanade.chaseweatherapp.core.api.OpenWeatherApi
import com.vipulkanade.chaseweatherapp.core.api.repository.OpenWeatherRepository
import com.vipulkanade.chaseweatherapp.core.model.Location
import com.vipulkanade.chaseweatherapp.core.model.Units
import com.vipulkanade.chaseweatherapp.data.remote.WeatherResponse
import com.vipulkanade.chaseweatherapp.data.repository.OpenWeatherRepositoryImpl
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.Response

class WeatherRepositoryUnitTest {

    @MockK
    val mockOpenWeatherApi = mockk<OpenWeatherApi>(relaxed = true)
    @Test
    fun `when we fetch weather data successfully, then a successfully mapped result is emitted`() = runBlocking {
        coEvery {
            mockOpenWeatherApi.getWeatherData(
                any(),
                any(),
                any(),
                any(),
            )
        } returns Response.success<WeatherResponse>(
            mockSuccessOpenWeatherResponse
        )

        val weatherRepository = createWeatherRepository()

        val expectedResult = mockSuccessWeatherResponse

        weatherRepository.fetchWeatherData(
            location = Location(
                longitude = 10.0,
                latitude = 12.90
            ),
            units = Units.IMPERIAL
        ).test {
            awaitItem().also { result ->
                assert(result.getOrThrow() == expectedResult)
            }
            awaitComplete()
        }
    }

    @Test
    fun `when we fetch weather data and an error occurs, then an error is emitted`() = runBlocking {
        coEvery {
            mockOpenWeatherApi.getWeatherData(
                any(),
                any(),
                any(),
                any(),
            )
        } returns Response.error<WeatherResponse>(
            404,
            "{}".toResponseBody()
        )

        val weatherRepository = createWeatherRepository()

        weatherRepository.fetchWeatherData(
            location = Location(
                longitude = 10.0,
                latitude = 12.90
            ),
            units = Units.IMPERIAL
        ).test {
            awaitItem().also { result ->
                assert(result.exceptionOrNull() is Throwable)
            }
            awaitComplete()
        }
    }

    private fun createWeatherRepository(): OpenWeatherRepository = OpenWeatherRepositoryImpl(
        api = mockOpenWeatherApi
    )
}
