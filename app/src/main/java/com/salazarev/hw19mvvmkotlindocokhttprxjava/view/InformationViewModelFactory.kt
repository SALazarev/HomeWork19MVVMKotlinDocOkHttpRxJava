package com.salazarev.hw19mvvmkotlindocokhttprxjava.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.salazarev.hw19mvvmkotlindocokhttprxjava.domain.QuotationInteractor
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.information.InformationViewModel
import javax.inject.Inject
import javax.inject.Named

class InformationViewModelFactory @Inject constructor(
    private val interactor: QuotationInteractor,
    @Named("id") private val id: String
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(aClass: Class<T>): T {
        return InformationViewModel(interactor, id) as T
    }
}