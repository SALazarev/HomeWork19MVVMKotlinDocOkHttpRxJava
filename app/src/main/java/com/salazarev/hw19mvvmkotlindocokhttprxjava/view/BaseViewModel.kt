package com.salazarev.hw19mvvmkotlindocokhttprxjava.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

/**
 * Базовый класс ViewModel.
 */
abstract class BaseViewModel : ViewModel() {

    /**
     * Наблюдаемое хранилище состояния загрузки данных.
     */
    val progress = MutableLiveData<Boolean>()

    /**
     * Наблюдаемое хранилище ошибки.
     */
    var errors = MutableLiveData<Throwable>()

    /**
     * Хранилище утилизаторов ресурсов.
     */
    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    /**
     * Добавление утилизатора к контейнеру.
     */
    fun Disposable.addTo(compositeDisposable: CompositeDisposable) = compositeDisposable.add(this)

    /**
     * Повторный запрос в сеть.
     */
    abstract fun tryAgain()
}