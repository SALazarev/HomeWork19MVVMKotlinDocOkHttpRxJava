package com.salazarev.hw19mvvmkotlindocokhttprxjava.view.information

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.salazarev.hw19mvvmkotlindocokhttprxjava.domain.QuotationInteractor
import com.salazarev.hw19mvvmkotlindocokhttprxjava.models.domain.Quotation
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class InformationViewModel(interactor: QuotationInteractor, id: String) : ViewModel() {

    val quotation = MutableLiveData<Double>()
    val progress = MutableLiveData<Boolean>()

    init {
        val productsList: Single<Quotation> = Single.fromCallable {
            return@fromCallable interactor.getQuotation(id)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { progress.value = false }
            .doOnSubscribe { progress.value = true }

        productsList.subscribe { item: Quotation, throwable: Throwable? ->
            if (throwable == null) quotation.postValue(item.price)
        }
    }

}