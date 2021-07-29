package com.example.rweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rweather.databinding.MainActivityBinding
import com.example.rweather.ui.main.MainFragment

class MainActivity : AppCompatActivity() {
    //Чтоб избавиться от ошибок с R.id
    //Связь layout с activity
    private lateinit var binding: MainActivityBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Связь layout с activity
        binding = MainActivityBinding.inflate(layoutInflater)
        //Чтоб избавиться от ошибок с R.id
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(binding.container.id, MainFragment.newInstance())
                    .commitNow()
        }
    }
}