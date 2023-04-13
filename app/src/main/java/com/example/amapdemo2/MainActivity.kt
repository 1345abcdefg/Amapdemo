package com.example.amapdemo2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.amapdemo2.data.Weather
import com.example.amapdemo2.data.WeatherShowInfo
import com.example.amapdemo2.ui.theme.AmapDemo2Theme
import org.koin.androidx.compose.getViewModel



class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AmapDemo2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting()
                }
            }
        }


    }
}

@Composable
fun Greeting() {
    listView()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AmapDemo2Theme {
        Greeting()
    }
}

@Composable
fun listView(viewModel: AmapViewModel = getViewModel()) {

    val value = viewModel.weatherLiveData.observeAsState().value
    value?.let {
        lazyColumn(it)
    } ?: lazyColumn(viewModel.list)
    viewModel.requestWeather()
}

@Composable
fun lazyColumn(list: MutableList<WeatherShowInfo>) {
    LazyColumn(state = rememberLazyListState()) {
        items(list.size) { index ->
            val weather = list[index]
            item(weather)
        }
    }
}

suspend fun requestWeather(city: String): Weather {
    return createApi().weatherInfo(city = city)
}


@Composable
fun item(weather: WeatherShowInfo) {
    Row(modifier = Modifier.padding(all = 12.dp)) {

        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(
                text = weather.cityName,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "天气：${weather.weather}",
                fontSize = 11.sp
            )
            Text(
                text = "温度：${weather.temperature}",
                fontSize = 11.sp
            )
            Text(
                text = "风向：${weather.winddirection}",
                fontSize = 11.sp
            )
            Text(
                text = "风力：${weather.windpower}",
                fontSize = 11.sp
            )
            Text(
                text = "空气温度：${weather.humidity}",
                fontSize = 11.sp
            )
            Text(
                text = "发布时间：${weather.reporttime}",
                fontSize = 11.sp
            )
        }
    }
}


fun createApi(): Api {
    return RetrofitFactory.intiRetrofit().create(Api::class.java)
}