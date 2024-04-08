package com.arunasbedzinskas.movio.ui.base.adapter

import androidx.annotation.CallSuper
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.arunasbedzinskas.movio.models.ui.AdapterItem
import com.arunasbedzinskas.movio.ui.base.BaseViewHolder

abstract class AutoBindAdapter(
    itemCallback: DiffUtil.ItemCallback<AdapterItem>
) : ListAdapter<AdapterItem, BaseViewHolder>(itemCallback) {

    @CallSuper
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)
    }

     override fun getItemViewType(position: Int): Int = getItem(position).viewType
}
