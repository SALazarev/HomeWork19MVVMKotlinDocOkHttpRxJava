package com.salazarev.hw19mvvmkotlindocokhttprxjava.view.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.salazarev.hw19mvvmkotlindocokhttprxjava.domain.QuotationInteractor
import com.salazarev.hw19mvvmkotlindocokhttprxjava.models.data.Quotation
import com.salazarev.hw19mvvmkotlindocokhttprxjava.models.view.Item
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class ListViewModel : ViewModel() {

    val liveData = MutableLiveData<List<Item>>()

    private val interactor: QuotationInteractor = QuotationInteractor()

    init {
        val productsList: Single<List<Quotation>> = Single.fromCallable {
            return@fromCallable interactor.getLast30Quotation()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        productsList.subscribe { list: List<Quotation>, throwable: Throwable? ->
            if (throwable == null) liveData.postValue(list.map { Item(it) })
        }


    }
}