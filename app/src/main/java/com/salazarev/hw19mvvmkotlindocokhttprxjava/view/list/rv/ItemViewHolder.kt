package com.salazarev.hw19mvvmkotlindocokhttprxjava.view.list.rv

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.salazarev.hw19mvvmkotlindocokhttprxjava.databinding.ItemRvBinding

class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding: ItemRvBinding = ItemRvBinding.bind(view)
    private val tv: TextView

    init {
        tv = binding.tv
    }

    fun setData(text: String) {
        tv.text = text
    }

    fun setOnClick(clickListener: View.OnClickListener) {
        tv.setOnClickListener(clickListener)
    }

}