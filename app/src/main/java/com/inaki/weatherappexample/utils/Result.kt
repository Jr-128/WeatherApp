package com.inaki.weatherappexample.utils

import com.inaki.weatherappexample.model.CityForecast

sealed class Result {
    class LOADING(val isLoading: Boolean = true): Result()
    class SUCCESS(val cityForecast: CityForecast): Result()
    class ERROR(val error: Throwable): Result()
}
