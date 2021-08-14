package com.salazarev.hw19mvvmkotlindocokhttprxjava.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.salazarev.hw19mvvmkotlindocokhttprxjava.domain.QuotationInteractor
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.information.InformationViewModel
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.list.ListViewModel
import dagger.Module
import dagger.Provides

//@Module
//interface ViewModelModule {
//    companion object{
//        @Provides
//        fun provideInfromationViewModel(interactor: QuotationInteractor,
//                                        id: String): InformationViewModel{
//            return ViewModelProvider(this, object : ViewModelProvider.Factory {
//                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//                    return InformationViewModel(interactor, id) as T
//                }
//            }).get(InformationViewModel::class.java)
//        }
//        @Provides
//        fun provideListViewModel(interactor: QuotationInteractor): ListViewModel{
//            return ViewModelProvider(this, object : ViewModelProvider.Factory {
//                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//                    return ListViewModel(interactor) as T
//                }
//            }).get(ListViewModel::class.java)
//        }
//    }
//}