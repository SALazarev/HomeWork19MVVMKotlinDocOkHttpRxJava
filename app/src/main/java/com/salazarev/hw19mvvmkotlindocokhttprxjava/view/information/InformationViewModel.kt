package com.salazarev.hw19mvvmkotlindocokhttprxjava.view.information

import androidx.lifecycle.MutableLiveData
import com.salazarev.hw19mvvmkotlindocokhttprxjava.domain.QuotationInteractor
import com.salazarev.hw19mvvmkotlindocokhttprxjava.models.domain.Quotation
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class InformationViewModel(
    private val interactor: QuotationInteractor,
    private val id: String
) : BaseViewModel() {

    val quotation = MutableLiveData<Quotation>()

    init {
        loadQuotation()
    }

    private fun loadQuotation() {
        Single.fromCallable {
            return@fromCallable interactor.getQuotation(id)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { progress.value = false }
            .doOnSubscribe { progress.value = true }
            .subscribe(quotation::setValue, errors::setValue)
            .addTo(compositeDisposable)
    }

    fun tryAgain() {
        loadQuotation()
    }
}