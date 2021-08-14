package com.salazarev.hw19mvvmkotlindocokhttprxjava.di.viewmodel

import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.information.InformationActivity
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Named

@ViewModelScope
@Subcomponent
interface ViewModelComponent {
    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun id(@Named("id") id: String): Builder
        fun build(): ViewModelComponent
    }

    fun inject(informationActivity: InformationActivity)
}