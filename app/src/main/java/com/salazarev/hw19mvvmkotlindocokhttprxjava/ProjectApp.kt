package com.salazarev.hw19mvvmkotlindocokhttprxjava

import android.app.Application
import com.salazarev.hw19mvvmkotlindocokhttprxjava.di.AppComponent
import com.salazarev.hw19mvvmkotlindocokhttprxjava.di.DaggerAppComponent

class ProjectApp: Application() {
    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        component =
            DaggerAppComponent.factory()
                .create(this,this)
    }
}