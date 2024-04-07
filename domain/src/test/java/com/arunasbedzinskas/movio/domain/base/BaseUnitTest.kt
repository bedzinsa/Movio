package com.arunasbedzinskas.movio.domain.base

import com.arunasbedzinskas.movio.core.dispatcher.AppDispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.Rule

abstract class BaseUnitTest {

    private val testScheduler = StandardTestDispatcher()
    protected val dispatchers = AppDispatchers(testScheduler, testScheduler, testScheduler)

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainRule = MainCoroutineRule(testScheduler)
}