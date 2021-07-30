package com.example.rweather.model

import com.example.rweather.model.data.Weather

//Всего у нас будет три состояния приложения:
//Success: приложение работает, данные отображаются.
//Loading: приложение загружает данные.
//Error: в приложении произошла какая-то ошибка.

sealed class AppState {
    data class Success(val  weatherData: Weather) : AppState()
    class Error(val error: Throwable): AppState()
    object Loading: AppState()
}