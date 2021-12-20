package com.inaki.weatherappexample.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.inaki.weatherappexample.R
import com.inaki.weatherappexample.databinding.FragmentCityBinding
import com.inaki.weatherappexample.viewmodel.ForecastViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CityFragment : Fragment() {
    private var param2: String? = null

    private lateinit var binding: FragmentCityBinding

    private val viewModel by viewModel<ForecastViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCityBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.cityName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.cityName = binding.cityName.text.toString()
            }

        })

        binding.searchForecast.setOnClickListener {
            Log.d("VIEWMODEL1", binding.cityName.text.toString())
            findNavController().navigate(R.id.CityForecastFragment)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = CityFragment()
    }
}