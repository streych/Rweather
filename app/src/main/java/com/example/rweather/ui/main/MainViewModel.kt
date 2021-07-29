package com.example.rweather.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Thread.sleep

//наследуемся от ViewModel
//добавим констркутор
class MainViewModel (private val liveDataToObserve: MutableLiveData<Any> = MutableLiveData()) :
    ViewModel() {
    //Передаём в конструктор LiveData, а точнее, реализацию LiveData
        fun getData(): LiveData<Any>{
        getDataFromLocalSource()
            return liveDataToObserve
        }

    //Мы добавили метод getDataFromLocalSource,
    // который имитирует запрос к БД или ещё какому-то источнику данных в приложении.
    // Запрос осуществляется асинхронно в отдельном потоке. Как только поток «просыпается»,
    // передаём в нашу LiveData какие-то данные через метод postValue. Если данные передаются в
    // основном потоке, используем метод setValue.
    private fun getDataFromLocalSource() {
        Thread{
            sleep(1000);
            liveDataToObserve.postValue((Any()))
        }.start()
    }
}