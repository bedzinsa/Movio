package com.arunasbedzinskas.movio.ui.ext

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun Fragment.navigate(
    @IdRes idRes: Int,
    args: Bundle? = null,
    navOptions: NavOptions = slideNavOptions()
) = findNavController().navigate(idRes, args, navOptions)

fun Fragment.navigate(
    navDirections: NavDirections,
    navOptions: NavOptions = slideNavOptions()
) = findNavController().navigate(navDirections, navOptions)

fun Fragment.navigateUp() = findNavController().navigateUp()

fun Fragment.popBackStack() = findNavController().popBackStack()

fun Fragment.repeatOnStarted(
    block: suspend CoroutineScope.() -> Unit
) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED, block)
    }
}
