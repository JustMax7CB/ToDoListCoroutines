package com.example.todolistcoroutines

import android.app.Application
import com.example.todolistcoroutines.di.appRepository
import com.example.todolistcoroutines.di.databaseModule
import com.example.todolistcoroutines.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(appComponent)
        }
    }

    private val appComponent = listOf(
        databaseModule,
        appRepository,
        viewModelModule,
    )


}