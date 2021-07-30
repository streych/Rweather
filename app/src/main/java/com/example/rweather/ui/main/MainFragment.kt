package com.example.rweather.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rweather.R
import com.example.rweather.databinding.MainFragmentBinding
import com.example.rweather.model.AppState
import com.example.rweather.model.data.Weather
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private var  _binding: MainFragmentBinding? = null
    private val binding
    get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

//Важно! Обязательно обнуляем _binding в onDestroyView, чтобы избежать утечек и не желаемого поведения.
// В Activity ничего похожего делать не требуется.
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        //Мы создали инстанс Observer, он выполняет метод renderData, как только LiveData обновляет данные,
        // которые она хранит. В качестве аргумента renderData принимает объект, возвращаемый лайвдатой.

        //вызываем у созданной ViewModel метод getData, возвращающий нам LiveData, и вызываем у LiveData метод observe,
        // который и передаём в жизненный цикл вместе с Observer. Теперь, если данные,
        // которые хранит LiveData, изменятся, Observer сразу об этом узнает и вызовет метод renderData,
        // куда передаст новые данные.

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getLivaData().observe(viewLifecycleOwner, Observer { a -> renderData(a) })

        //val observer = Observer<AppState> { a -> renderData(a) }
        //viewModel.get().observe(viewLifecycleOwner, observer)
        viewModel.getWeatherFromRemoateSource()
    }
    private fun renderData(data: AppState) {
        when(data){
            is AppState.Success -> {
                val weatherData = data.weatherData
                binding.loadingLayout.visibility = View.GONE
                populateData(weatherData)
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error ->{
                binding.loadingLayout.visibility = View.GONE
                Snackbar.make(binding.mainView, "Error", Snackbar.LENGTH_INDEFINITE).show()
            }
        }
        //Toast.makeText(context, "data", Toast.LENGTH_LONG).show()
        //Snackbar.make(context, "data", Snackbar.LENGTH_LONG)

        Toast.makeText(context, "It is a toast ", Toast.LENGTH_SHORT).show();
    }

    private fun populateData(weatherData: Weather) {
        with(binding) {
            cityName.text = weatherData.city.city
            cityCoordinates.text = String.format(
                getString(R.string.city_coordinates),
                weatherData.city.lat.toString(),
                weatherData.city.lon.toString()
            )
            temperatureValue.text = weatherData.temperature.toString()
            feelsLikeValue.text = weatherData.feelsLike.toString()
        }
    }

}