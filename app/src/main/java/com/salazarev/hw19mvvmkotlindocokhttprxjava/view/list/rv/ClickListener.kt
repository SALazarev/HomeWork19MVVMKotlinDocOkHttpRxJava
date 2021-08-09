package com.salazarev.hw19mvvmkotlindocokhttprxjava.view.list.rv

/**
 * Слушатель нажатия на элемент списка.
 */
interface ClickListener {
    /**
     * Метод обработки нажатия на элемент списка.
     * @param id Идентификатор элемента.
     */
    fun onClick(id: String)
}