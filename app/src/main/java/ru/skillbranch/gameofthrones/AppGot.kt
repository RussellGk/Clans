package ru.skillbranch.gameofthrones

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.skillbranch.gameofthrones.di.dataModule

class AppGot : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidContext(this@AppGot)
            modules(dataModule)
        }
        NetworkMonitor(this).startNetworkCallback()
    }

    override fun onTerminate() {
        super.onTerminate()
        NetworkMonitor(this).stopNetworkCallback()
    }

    companion object {
        lateinit var instance: AppGot
    }
}