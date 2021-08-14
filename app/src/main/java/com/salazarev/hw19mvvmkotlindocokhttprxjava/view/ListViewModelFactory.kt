package com.salazarev.hw19mvvmkotlindocokhttprxjava.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.salazarev.hw19mvvmkotlindocokhttprxjava.domain.QuotationInteractor
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.list.ListViewModel
import javax.inject.Inject


class ListViewModelFactory @Inject constructor(private val interactor: QuotationInteractor) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(aClass: Class<T>): T {
        return ListViewModel(interactor) as T
    }
}