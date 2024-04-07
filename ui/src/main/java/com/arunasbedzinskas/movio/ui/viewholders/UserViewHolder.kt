package com.arunasbedzinskas.movio.ui.viewholders

import com.arunasbedzinskas.movio.models.ui.AdapterItem
import com.arunasbedzinskas.movio.models.ui.UserDataUI
import com.arunasbedzinskas.movio.ui.base.BaseViewHolder
import com.arunasbedzinskas.movio.ui.databinding.HolderUserBinding

class UserViewHolder(val binding: HolderUserBinding) : BaseViewHolder(binding) {

    override fun onBind(item: AdapterItem) {
        if (item !is AdapterItem.UserItem) return
        initUI(item.userDataUI)
    }

    private fun initUI(userDataUI: UserDataUI) {
        with(binding) {
            ivHeaderAvatar.setImageResource(userDataUI.avatar)
            tvHeaderUserName.text = userDataUI.name
        }
    }
}
