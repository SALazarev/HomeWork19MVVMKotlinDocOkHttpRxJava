package com.salazarev.hw19mvvmkotlindocokhttprxjava.di

import com.salazarev.hw19mvvmkotlindocokhttprxjava.di.viewmodel.ViewModelComponent
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.list.ListActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun getViewModelComponent(): ViewModelComponent.Builder
    fun inject(listActivity: ListActivity)
}