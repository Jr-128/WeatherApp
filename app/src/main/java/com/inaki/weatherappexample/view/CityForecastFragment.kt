package com.inaki.weatherappexample.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.inaki.weatherappexample.R
import com.inaki.weatherappexample.adapter.ForecastAdapter
import com.inaki.weatherappexample.adapter.ForecastDetailsClick
import com.inaki.weatherappexample.databinding.FragmentCityForecastBinding
import com.inaki.weatherappexample.model.CityForecast
import com.inaki.weatherappexample.model.Forecast
import com.inaki.weatherappexample.utils.Result
import com.inaki.weatherappexample.viewmodel.ForecastViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CityForecastFragment : Fragment(), ForecastDetailsClick {
    // this variable is for view binding
    private lateinit var binding: FragmentCityForecastBinding

    private lateinit var forecastAdapter: ForecastAdapter

    private val viewModel: ForecastViewModel by viewModel<ForecastViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // assigning the forecast adapter with the new instance of the adapter
        forecastAdapter = ForecastAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCityForecastBinding.inflate(inflater, container, false)

        viewModel.cityForecastObserver.observe(viewLifecycleOwner, ::handleResult)

        viewModel.getCityForecast()

        // here I am setting my recycler view
        binding.forecastRecycler.apply {
            // adding the layout manager
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            // setting the adapter
            adapter = forecastAdapter
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        // HERE we are going to make the network call

    }

    private fun handleResult(result: Result) {
        when(result) {
            is Result.LOADING -> {  }
            is Result.SUCCESS -> { handleSuccess(result.cityForecast) }
            is Result.ERROR -> { handleError(result.error) }
        }
    }

    private fun handleSuccess(forecast: CityForecast) {
        forecastAdapter.updateForecast(forecast)
    }

    private fun handleError(throwable: Throwable) {
        Toast.makeText(requireContext(), throwable.localizedMessage, Toast.LENGTH_LONG).show()
    }

    override fun moveToForecastDetails(forecast: Forecast) {
        viewModel.setSpecificForecast(forecast)

        viewModel.cityForecastObserver.removeObservers(viewLifecycleOwner)

        findNavController().navigate(R.id.ForecastDetailsFragment)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = CityForecastFragment()
    }
}