package com.inaki.weatherappexample.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.inaki.weatherappexample.R
import com.inaki.weatherappexample.databinding.FragmentCityBinding
import com.inaki.weatherappexample.databinding.FragmentForecastDetailsBinding
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

private const val WEATHER = "weather"
private const val DESCRIPTION = "description"
private const val PRESSURE = "pressure"
private const val FEELS_LIKE = "feels_like"
private const val HUMIDITY = "humidity"
private const val TEMP = "temp"
private const val MAX = "temp_max"
private const val MIN = "temp_min"

/**
 * A simple [Fragment] subclass.
 * Use the [ForecastDetails.newInstance] factory method to
 * create an instance of this fragment.
 */
class ForecastDetails : Fragment() {

    // this variable is for view binding
    private lateinit var binding: FragmentForecastDetailsBinding

    private var weather: String? = null
    private var description: String? = null
    private var pressure: Int? = null
    private var feels_like: Double? = null
    private var humidity: Int? = null
    private var temp: Double? = null
    private var max: Double? = null
    private var min: Double? = null
    private var tempFahrenheit: Double? = null
    private var maxFahrenheit: Double? = null
    private var minFahrenheit: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            weather = it.getString(WEATHER)
            description = it.getString(DESCRIPTION)
            pressure = it.getInt(PRESSURE)
            feels_like = it.getDouble(FEELS_LIKE)
            humidity = it.getInt(HUMIDITY)
            temp = it.getDouble(TEMP)
            max = it.getDouble(MAX)
            min = it.getDouble(MIN)


            Log.d("ForecastDetails", "\nWeather :  " + weather)
            Log.d("ForecastDetails", "Description :  " + description)
            Log.d("ForecastDetails", "Pressure :  " + pressure.toString())
            Log.d("ForecastDetails", "Feels like :  " + feels_like.toString())
            Log.d("ForecastDetails", "Humidity :  " + humidity.toString())
            Log.d("ForecastDetails", "Temperature :  " + temp.toString())
            Log.d("ForecastDetails", "Max :  " + max.toString())
            Log.d("ForecastDetails", "Min :  " + min.toString())
        }
    }


    override fun onResume() {
        super.onResume()

        tempFahrenheit = (((temp?.minus(273.15))?.times(9 / 5)?.plus(32)))
        maxFahrenheit = (((max?.minus(273.15))?.times(9 / 5)?.plus(32)))
        minFahrenheit = (((min?.minus(273.15))?.times(9 / 5)?.plus(32)))


        binding.weather1.text = weather

        binding.description1.text = description!!

        binding.pressure1.text = pressure!!.toString()

        binding.feellike1.text = feels_like!!.toString()

        binding.humidity1.text = humidity!!.toString()

        binding.temp1.text = tempFahrenheit!!.toString()

        binding.max1.text = maxFahrenheit!!.toString()

        binding.min1.text = minFahrenheit!!.toString()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentForecastDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment ForecastDetails.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ForecastDetails().apply {
                arguments = Bundle().apply {
                    putString(WEATHER, weather)
                    putString(DESCRIPTION, description)
                    putInt(PRESSURE, pressure!!)
                    putDouble(FEELS_LIKE, feels_like!!)
                    putInt(HUMIDITY, humidity!!)
                    putDouble(TEMP, temp!!)
                    putDouble(MAX, max!!)
                    putDouble(MIN, min!!)
                }
            }
    }

}