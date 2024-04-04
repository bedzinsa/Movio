package com.arunasbedzinskas.movio.domain.mock

import com.arunasbedzinskas.movio.data.datastore.LocalDataStore
import com.arunasbedzinskas.movio.models.data.UserData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf

object LocalDataStoreMock {

    fun mockForUserData(storedUser: UserData?) = mockk<LocalDataStore>(relaxed = true) {
        coEvery { setUser(any()) } returns Unit
        coEvery { getUser() } returns flowOf(storedUser)
    }
}
