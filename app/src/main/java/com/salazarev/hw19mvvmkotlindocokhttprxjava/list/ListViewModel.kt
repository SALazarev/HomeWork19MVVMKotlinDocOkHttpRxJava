package com.salazarev.hw19mvvmkotlindocokhttprxjava.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.salazarev.hw19mvvmkotlindocokhttprxjava.list.rv.Item

val liveData = MutableLiveData<List<Item>>()

class ListViewModel : ViewModel() {
    init {
        liveData.postValue(listOf(Item("1", "hey"), Item("2", "hoy"), Item("3", "hay")))
    }
}