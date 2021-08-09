package com.jungroup.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jungroup.weatherapp.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

import dagger.hilt.android.AndroidEntryPoint

import android.view.View
import androidx.datastore.preferences.core.stringPreferencesKey
import com.jungroup.weatherapp.adapters.ForecastAdapter
import kotlinx.coroutines.flow.first


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val forecastViewModel: ForecastViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = ForecastAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)


        lifecycleScope.launch launchWhenCreated@{
            val response = try {
                //GET FORECAST DATA
                forecastViewModel.getForecastResponse(applicationContext)
            } catch (e: IOException) {
                Log.d("IOEXCEPTION", e.message.toString())
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.d("HTTPEXCEPTION", e.stackTrace.toString())
                return@launchWhenCreated
            }
            //PLACES FORECAST INTO RECYCLERVIEW
            adapter.submitList(response!!.forecastProperties.periods)
            //REMOVES SPINNER
            binding.spinner.visibility = View.GONE
            //READ DATASTORE OBJECT AND CHANGE TITLEBAR TO CITY NAME
            val dataStoreKey = stringPreferencesKey("city")
            val city = applicationContext.dataStore.data.first()
            binding.toolbarText.text = city[dataStoreKey]
        }

    }

}