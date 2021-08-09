package com.salazarev.hw19mvvmkotlindocokhttprxjava.util.json

/**
 * Интерфейс обработчика запросов json.
 */
interface JsonWorker {
    /**
     * Метод преобразования json запроса в объект.
     * @param classType Тип объекта.
     * @return Объект определённого типа.
     */
    fun <T> getObject(request: String, classType: Class<T>): T

    /**
     * Метод преобразования json запроса в список объектов.
     * @param classType Пип объектов.
     * @return Список объектов определённого типа.
     */
    fun <T> getObjectList(request: String, classType: Class<T>): List<T>
}