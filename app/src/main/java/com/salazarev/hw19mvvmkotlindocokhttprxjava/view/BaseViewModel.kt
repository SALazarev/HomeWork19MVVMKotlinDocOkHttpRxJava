package com.salazarev.hw19mvvmkotlindocokhttprxjava.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

open class BaseViewModel : ViewModel() {

    val progress = MutableLiveData<Boolean>()
    var errors = MutableLiveData<Throwable>()

    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun Disposable.addTo(compositeDisposable: CompositeDisposable) = compositeDisposable.add(this)
}