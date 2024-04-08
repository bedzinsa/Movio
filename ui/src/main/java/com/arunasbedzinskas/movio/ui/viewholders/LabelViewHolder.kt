package com.arunasbedzinskas.movio.ui.viewholders

import com.arunasbedzinskas.movio.models.ui.AdapterItem
import com.arunasbedzinskas.movio.ui.base.BaseViewHolder
import com.arunasbedzinskas.movio.ui.databinding.HolderLabelBinding

class LabelViewHolder(private val binding: HolderLabelBinding) : BaseViewHolder(binding) {

    override fun onBind(item: AdapterItem) {
        if (item !is AdapterItem.LabelItem) return
        binding.tvLabel.text = item.labelText
    }
}
