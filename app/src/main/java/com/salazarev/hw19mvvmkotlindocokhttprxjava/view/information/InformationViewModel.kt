package com.salazarev.hw19mvvmkotlindocokhttprxjava.view.information

import androidx.lifecycle.MutableLiveData
import com.salazarev.hw19mvvmkotlindocokhttprxjava.domain.QuotationInteractor
import com.salazarev.hw19mvvmkotlindocokhttprxjava.models.domain.Quotation
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * ViewModel экрана информации о котировке.
 * @param interactor Объект работы с котировкой цены на золото.
 * @param date Дата котировки.
 * @constructor Загрузка данных из сети.
 */
class InformationViewModel(
    private val interactor: QuotationInteractor,
    private val date: String
) : BaseViewModel() {

    /**
     * Наблюдаемое хранилище данных котировки цены на золото.
     */
    val quotation = MutableLiveData<Quotation>()

    init {
        loadQuotation()
    }

    private fun loadQuotation() {
        Single.fromCallable {
            return@fromCallable interactor.getQuotation(date)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { progress.value = false }
            .doOnSubscribe { progress.value = true }
            .subscribe(quotation::setValue, errors::setValue)
            .addTo(compositeDisposable)
    }

    override fun tryAgain() {
        loadQuotation()
    }
}