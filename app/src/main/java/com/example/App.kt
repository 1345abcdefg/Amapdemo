package com.example

import android.app.Application
import com.example.amapdemo2.AmapViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModelOf

import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {
    val appModule = module {
        viewModelOf(::AmapViewModel)
    }
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }

    }


}