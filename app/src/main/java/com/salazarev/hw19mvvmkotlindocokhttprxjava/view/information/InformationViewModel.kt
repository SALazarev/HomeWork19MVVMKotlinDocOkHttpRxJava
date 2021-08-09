package com.salazarev.hw19mvvmkotlindocokhttprxjava.view.information

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.salazarev.hw19mvvmkotlindocokhttprxjava.domain.QuotationInteractor
import com.salazarev.hw19mvvmkotlindocokhttprxjava.models.data.Quotation
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class InformationViewModel(id: String) : ViewModel() {

    val liveData = MutableLiveData<Double>()

    val interactor: QuotationInteractor = QuotationInteractor()

    init {

        val productsList: Single<Quotation> = Single.fromCallable {
            return@fromCallable interactor.getQuotation(id)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        productsList.subscribe { item: Quotation, throwable: Throwable? ->
            if (throwable == null) liveData.postValue(item.price)
        }


    }

}