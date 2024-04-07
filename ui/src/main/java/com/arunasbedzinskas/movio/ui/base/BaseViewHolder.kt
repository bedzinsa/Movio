package com.arunasbedzinskas.movio.ui.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.arunasbedzinskas.movio.models.ui.AdapterItem

abstract class BaseViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    abstract fun onBind(item: AdapterItem)
}
