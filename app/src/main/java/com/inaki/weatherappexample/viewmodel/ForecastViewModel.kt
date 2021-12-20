package com.inaki.weatherappexample.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inaki.weatherappexample.model.Forecast
import com.inaki.weatherappexample.rest.NetworkApi
import com.inaki.weatherappexample.utils.Result
import kotlinx.coroutines.*

class ForecastViewModel(
    private val networkApi: NetworkApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val coroutineScope: CoroutineScope = CoroutineScope(ioDispatcher)
) : ViewModel() {

    var cityName: String = ""

    private var _cityForecast: MutableLiveData<Result> = MutableLiveData(Result.LOADING())
    val cityForecastObserver: LiveData<Result> get() = _cityForecast

    private var _specificForecast: MutableLiveData<Forecast?> = MutableLiveData(null)
    val specificForecastObserver: LiveData<Forecast?> get() = _specificForecast

    fun setSpecificForecast(cityForecast: Forecast) {
        _specificForecast.postValue(cityForecast)
    }

    fun getCityForecast() {
        Log.d("VIEWMODEL", cityName)

        coroutineScope.launch {
            try {
                if (cityName.isEmpty()) {
                    _cityForecast.postValue(Result.ERROR(IllegalArgumentException("Provide a valid city")))
                }
                val response = networkApi.getForecast(cityName)
                response.body()?.let {
                    _cityForecast.postValue(Result.SUCCESS(it))
                } ?: _cityForecast.postValue(Result.ERROR(Throwable("Response is null")))
            } catch (e: Exception) {
                _cityForecast.postValue(Result.ERROR(e))
            }
        }
    }
}