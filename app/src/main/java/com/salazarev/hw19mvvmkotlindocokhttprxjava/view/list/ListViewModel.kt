package com.salazarev.hw19mvvmkotlindocokhttprxjava.view.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.salazarev.hw19mvvmkotlindocokhttprxjava.domain.QuotationInteractor
import com.salazarev.hw19mvvmkotlindocokhttprxjava.models.view.QuotationListItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class ListViewModel(interactor: QuotationInteractor) : ViewModel() {

    val quotations = MutableLiveData<List<QuotationListItem>>()
    val progress = MutableLiveData<Boolean>()

    init {
        val productsList: Single<List<QuotationListItem>> = Single.fromCallable {
            return@fromCallable interactor.getLast30Quotation()
        }
            .map { it.map { quotation -> QuotationListItem(quotation.date) } }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { progress.value = false }
            .doOnSubscribe { progress.value = true }

        productsList.subscribe { list: List<QuotationListItem>, throwable: Throwable? ->
            if (throwable == null) quotations.postValue(list)
        }
    }
}