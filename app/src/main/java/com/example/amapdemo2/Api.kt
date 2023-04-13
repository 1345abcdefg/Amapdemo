package com.example.amapdemo2

import com.example.amapdemo2.data.Weather
import retrofit2.http.*


interface Api {

    @GET("v3/weather/weatherInfo")
    suspend fun weatherInfo(@Query("key") key :String = "7a776da7353a5d8f1528d980451b76de",@Query("city") city :String) : Weather

}