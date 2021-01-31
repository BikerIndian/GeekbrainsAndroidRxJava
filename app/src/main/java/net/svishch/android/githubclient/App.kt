package net.svishch.android.githubclient

import android.app.Application
import net.svishch.android.githubclient.mvp.model.ModelDataProviders
import net.svishch.android.githubclient.mvp.model.entity.room.Database
import net.svishch.android.githubclient.di.AppComponent
import net.svishch.android.githubclient.di.DaggerAppComponent
import net.svishch.android.githubclient.di.modules.AppModule
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class App : Application() {
    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    lateinit var appComponent: AppComponent
        private set


    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        // DaggerAppComponent создается сам
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        Database.create(instance)
        ModelDataProviders.initNetMonitor()
    }

    val navigatorHolder
        get() = cicerone.navigatorHolder

    val router
        get() = cicerone.router

}