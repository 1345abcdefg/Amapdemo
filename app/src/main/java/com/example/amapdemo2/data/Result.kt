package com.example.amapdemo2.data


data class Weather(
    val count: String?,
    val info: String,
    val infocode: String,
    val lives: List<Live>,
    val status: String
)

data class Live(
    val adcode: String,
    val city: String,
    val humidity: String,
    val humidity_float: String,
    val province: String,
    val reporttime: String,
    val temperature: String,
    val temperature_float: String,
    val weather: String,
    val winddirection: String,
    val windpower: String
)
