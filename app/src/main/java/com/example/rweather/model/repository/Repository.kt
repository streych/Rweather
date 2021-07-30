package com.example.rweather.model.repository

import com.example.rweather.model.data.Weather

interface Repository {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLoacalStorage(): Weather
}