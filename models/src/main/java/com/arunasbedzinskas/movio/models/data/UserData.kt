package com.arunasbedzinskas.movio.models.data

data class UserData(
    val name: String,
    val email: String,
    val avatar: Int,
    val isCreated: Boolean = true
)
