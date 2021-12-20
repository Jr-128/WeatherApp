package com.inaki.weatherappexample.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.inaki.weatherappexample.databinding.FragmentForecastDetailsBinding
import com.inaki.weatherappexample.model.CityForecast
import com.inaki.weatherappexample.model.Forecast
import com.inaki.weatherappexample.utils.Result
import com.inaki.weatherappexample.viewmodel.ForecastViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class ForecastDetails : Fragment() {

    // this variable is for view binding
    private lateinit var binding: FragmentForecastDetailsBinding

    private val viewModel by viewModel<ForecastViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentForecastDetailsBinding.inflate(layoutInflater)

        viewModel.specificForecastObserver.observe(viewLifecycleOwner, ::handleResult)

        return binding.root
    }

    private fun handleResult(forecast: Forecast?) {
        forecast?.let {
            handleSuccess(it)
        }
    }

    private fun handleSuccess(cityForecast: Forecast) {
        val tempFahrenheit = (((cityForecast.main.temp.minus(273.15)).times(9 / 5).plus(32)))
        val maxFahrenheit = (((cityForecast.main.tempMax.minus(273.15)).times(9 / 5).plus(32)))
        val minFahrenheit = (((cityForecast.main.tempMin.minus(273.15)).times(9 / 5).plus(32)))

        binding.weather1.text = cityForecast.weather[0].main
        binding.description1.text = cityForecast.weather[0].description
        binding.pressure1.text = cityForecast.main.pressure.toString()
        binding.feellike1.text = cityForecast.main.feelsLike.toString()
        binding.humidity1.text = cityForecast.main.humidity.toString()
        binding.temp1.text = tempFahrenheit.toString()
        binding.max1.text = maxFahrenheit.toString()
        binding.min1.text = minFahrenheit.toString()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment ForecastDetails.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) = ForecastDetails()
    }

}