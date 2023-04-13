package com.example.amapdemo2.data

data class WeatherShowInfo(
    val cityName: String,
    val cityCode: String,
    //天气
    var weather: String ="-",
    //温度
    var temperature: String="-",
    //风向
    var winddirection: String="-",
    //风力
    var windpower:String="-",
    //空气湿度
    var humidity:String="-",
    //发布时间
    var reporttime:String="-"
)