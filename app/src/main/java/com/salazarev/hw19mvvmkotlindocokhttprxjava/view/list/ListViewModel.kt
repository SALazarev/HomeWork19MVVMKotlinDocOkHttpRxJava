package com.salazarev.hw19mvvmkotlindocokhttprxjava.view.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.salazarev.hw19mvvmkotlindocokhttprxjava.data.api.ClientApi
import com.salazarev.hw19mvvmkotlindocokhttprxjava.data.api.OkHttpApi
import com.salazarev.hw19mvvmkotlindocokhttprxjava.domain.QuotationInteractor
import com.salazarev.hw19mvvmkotlindocokhttprxjava.models.data.Quotation
import com.salazarev.hw19mvvmkotlindocokhttprxjava.models.view.Item
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

val liveData = MutableLiveData<List<Item>>()

val interactor: QuotationInteractor = QuotationInteractor()

class ListViewModel : ViewModel() {
    init {
        val productsList: Single<List<Quotation>> = Single.fromCallable {
            return@fromCallable interactor.getQuotation()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        productsList.subscribe { list: List<Quotation>, throwable: Throwable? ->
            if (throwable == null) liveData.postValue(list.map { Item(it) })
        }


    }
}