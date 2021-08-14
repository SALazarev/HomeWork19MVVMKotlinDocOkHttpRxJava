package com.salazarev.hw19mvvmkotlindocokhttprxjava.di

import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.information.InformationActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Named

@Component(modules = [AppModule::class])
interface ViewModelComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun id(@Named("id") id: String): Builder
        fun build(): ViewModelComponent
    }
    fun inject(informationActivity: InformationActivity)
}