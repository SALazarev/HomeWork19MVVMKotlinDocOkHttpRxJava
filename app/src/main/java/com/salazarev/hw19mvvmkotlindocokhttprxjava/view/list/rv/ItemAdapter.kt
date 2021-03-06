package com.salazarev.hw19mvvmkotlindocokhttprxjava.view.list.rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.salazarev.hw19mvvmkotlindocokhttprxjava.R
import com.salazarev.hw19mvvmkotlindocokhttprxjava.models.view.QuotationListItem

/**
 * Адаптер списка котировок
 * @param quotationListItems Список котировок
 */
class ItemAdapter(
    private var quotationListItems: List<QuotationListItem>,
    private val clickListener: ClickListener
) :
    RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = quotationListItems[position]
        holder.setData(item.date)
        holder.setOnClick { clickListener.onClick(item.date) }
    }

    override fun getItemCount(): Int {
        return quotationListItems.size
    }
}