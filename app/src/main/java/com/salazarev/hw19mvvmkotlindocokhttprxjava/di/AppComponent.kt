package com.salazarev.hw19mvvmkotlindocokhttprxjava.di

import android.content.Context
import com.salazarev.hw19mvvmkotlindocokhttprxjava.ProjectApp
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.information.InformationActivity
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.list.ListActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

//    @Component.Factory
//    interface Factory {
//        fun create(
//            @BindsInstance appContext: Context,
//            @BindsInstance app: ProjectApp
//        ): AppComponent
//    }

    fun inject(informationActivity: InformationActivity)
    fun inject(listActivity: ListActivity)

//    @Component.Builder
//    interface Builder{
//        @BindsInstance
//        fun id(id: String): AppComponent.Builder
//        fun build(): AppComponent.Builder
//    }
}