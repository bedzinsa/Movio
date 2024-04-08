package com.arunasbedzinskas.movio.core.ext

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> Gson.fromJson(json: String): List<T> {
    val listType = TypeToken.getParameterized(List::class.java, T::class.java).type
    return fromJson(json, listType)
}
