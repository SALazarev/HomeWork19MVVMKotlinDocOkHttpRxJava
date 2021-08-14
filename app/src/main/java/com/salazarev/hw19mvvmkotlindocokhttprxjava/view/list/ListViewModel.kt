package com.salazarev.hw19mvvmkotlindocokhttprxjava.view.list

import androidx.lifecycle.MutableLiveData
import com.salazarev.hw19mvvmkotlindocokhttprxjava.domain.QuotationInteractor
import com.salazarev.hw19mvvmkotlindocokhttprxjava.models.view.QuotationListItem
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

/**
 * ViewModel экрана информации о котировке.
 * @param interactor Объект работы с котировками цен на золото.
 * @constructor Загрузка данных из сети.
 */
class ListViewModel @Inject constructor(private val interactor: QuotationInteractor) :
    BaseViewModel() {

    /**
     * Наблюдаемое хранилище данных котировок цен на золото.
     */
    val quotations = MutableLiveData<List<QuotationListItem>>()

    init {
        loadQuotationList()
    }

    private fun loadQuotationList() {
        val productsList: Single<List<QuotationListItem>> = Single.fromCallable {
            return@fromCallable interactor.getQuotationList()
        }
            .map { it.map { quotation -> QuotationListItem(quotation.date) } }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { progress.value = false }
            .doOnSubscribe { progress.value = true }

        productsList
            .subscribe(quotations::setValue, errors::setValue)
            .addTo(compositeDisposable)
    }

    override fun tryAgain() {
        loadQuotationList()
    }
}