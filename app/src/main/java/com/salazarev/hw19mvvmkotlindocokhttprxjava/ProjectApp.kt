package com.salazarev.hw19mvvmkotlindocokhttprxjava

import android.app.Application
import android.content.Context
import com.salazarev.hw19mvvmkotlindocokhttprxjava.di.AppComponent
import com.salazarev.hw19mvvmkotlindocokhttprxjava.di.DaggerAppComponent

class ProjectApp : Application() {
    companion object {
        fun getAppComponent(context: Context) = (context.applicationContext as ProjectApp).component
    }

    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.create()
    }
}