package com.example.amapdemo2

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amapdemo2.data.Weather
import com.example.amapdemo2.data.WeatherShowInfo
import com.example.request
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.launch


class AmapViewModel : ViewModel() {

    val list = mutableListOf<WeatherShowInfo>().apply {
        add(WeatherShowInfo("北京市", "110000"))
        add(WeatherShowInfo("上海市", "310000"))
        add(WeatherShowInfo("广州市", "440100"))
        add(WeatherShowInfo("深圳市", "440300"))
        add(WeatherShowInfo("苏州市", "320500"))
        add(WeatherShowInfo("沈阳市", "210100"))
    }
    val newList = mutableListOf<WeatherShowInfo>()
    val taskList = mutableListOf<Deferred<Weather>>()
    val weatherLiveData =
        MutableLiveData<MutableList<WeatherShowInfo>>(list)


    fun requestWeather() {
        viewModelScope.launch {
            taskList.clear()
            list.forEach {
                taskList.add(
                    request(
                        { requestWeather(it.cityCode) },
                        this
                    )
                )
            }

            taskList.forEachIndexed { index, deferred ->
                try {
                    val weather = deferred.await()

                    if (TextUtils.equals(weather.infocode, "10000")) {
                        val lives = weather.lives
                        val live = lives[0]
                        val weatherShowInfo = WeatherShowInfo(
                            live.city,
                            live.adcode,
                            live.weather,
                            live.weather,
                            live.reporttime,
                            live.temperature,
                            live.winddirection,
                            live.windpower
                        )
                        newList.add(weatherShowInfo)
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                    val weatherShowInfo = list[index]
                    newList.add(weatherShowInfo)
                }finally {

                }
            }
            weatherLiveData.value = newList
        }


    }

}