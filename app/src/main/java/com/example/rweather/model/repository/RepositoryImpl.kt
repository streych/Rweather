package com.example.rweather.model.repository

import com.example.rweather.model.data.Weather

class RepositoryImpl: Repository {

    override fun getWeatherFromServer(): Weather {
        return Weather()
    }

    override fun getWeatherFromLoacalStorage(): Weather {
        return Weather()
    }

}