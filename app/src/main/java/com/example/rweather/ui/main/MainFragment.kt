package com.example.rweather.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.rweather.R
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        //Мы создали инстанс Observer, он выполняет метод renderData, как только LiveData обновляет данные,
        // которые она хранит. В качестве аргумента renderData принимает объект, возвращаемый лайвдатой.
        val observer = Observer<Any> { renderData(it)}
        //вызываем у созданной ViewModel метод getData, возвращающий нам LiveData, и вызываем у LiveData метод observe,
        // который и передаём в жизненный цикл вместе с Observer. Теперь, если данные,
        // которые хранит LiveData, изменятся, Observer сразу об этом узнает и вызовет метод renderData,
        // куда передаст новые данные.
        viewModel.getData().observe(viewLifecycleOwner, observer)
    }

    private fun renderData(data: Any) {
        //Toast.makeText(context, "data", Toast.LENGTH_LONG).show()
        //Snackbar.make(context, "data", Snackbar.LENGTH_LONG)
        Toast.makeText(context, "It is a toast ", Toast.LENGTH_SHORT).show();
    }

}