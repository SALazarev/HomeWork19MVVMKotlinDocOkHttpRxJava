package com.salazarev.hw19mvvmkotlindocokhttprxjava.view.list.rv

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.salazarev.hw19mvvmkotlindocokhttprxjava.databinding.ItemRvBinding

/**
 * ViewHolder адаптера списка котировок.
 */
class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding: ItemRvBinding = ItemRvBinding.bind(view)
    private val tv: TextView

    init {
        tv = binding.tv
    }

    /**
     * Установка в качестве текста элемента - даты котировки.
     * @param data Дата котировки.
     */
    fun setData(data: String) {
        tv.text = data
    }

    /**
     * Установка слушателя нажатия на элемент списка.
     */
    fun setOnClick(clickListener: View.OnClickListener) {
        tv.setOnClickListener(clickListener)
    }

}