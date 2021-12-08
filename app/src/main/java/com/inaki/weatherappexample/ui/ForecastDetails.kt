package com.inaki.weatherappexample.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.inaki.weatherappexample.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

private const val WEATHER = "weather"
private const val DESCRIPTION = "description"
private const val PRESSURE = "pressure"
private const val FEELS_LIKE = "feels like"
private const val HUMIDITY = "humidity"
private const val TEMP = "temp"
private const val MAX = "max"
private const val MIN = "min"

/**
 * A simple [Fragment] subclass.
 * Use the [ForecastDetails.newInstance] factory method to
 * create an instance of this fragment.
 */
class ForecastDetails : Fragment() {
    private var weather: String? = null
    private var description: String? = null
    private var pressure: Float? = null
    private var feels_like: Float? = null
    private var humidity: Float? = null
    private var temp: Float? = null
    private var max: Float? = null
    private var min: Float? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            weather = it.getString(WEATHER)
            description = it.getString(DESCRIPTION)
            pressure = it.getFloat(PRESSURE)
            feels_like = it.getFloat(FEELS_LIKE)
            humidity = it.getFloat(HUMIDITY)
            temp = it.getFloat(TEMP)
            max = it.getFloat(MAX)
            min = it.getFloat(MIN)


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forecast_details, container, false)
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
                    putFloat(PRESSURE, pressure!!.toFloat())
                    putFloat(FEELS_LIKE, feels_like!!.toFloat())
                    putFloat(HUMIDITY, humidity!!.toFloat())
                    putFloat(TEMP, temp!!.toFloat())
                    putFloat(MAX, max!!.toFloat())
                    putFloat(MIN, min!!.toFloat())
                }
            }
    }
}