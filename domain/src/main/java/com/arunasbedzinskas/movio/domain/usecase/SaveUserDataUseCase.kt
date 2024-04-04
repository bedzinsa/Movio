package com.arunasbedzinskas.movio.domain.usecase

import com.arunasbedzinskas.movio.data.datastore.LocalDataStore
import com.arunasbedzinskas.movio.models.data.UserData
import kotlinx.coroutines.flow.firstOrNull

fun interface SaveUserDataUseCase {

    suspend operator fun invoke(name: String, email: String): Boolean
}

internal class SaveUserDataUseCaseImpl(
    private val localDataStore: LocalDataStore
) : SaveUserDataUseCase {

    override suspend fun invoke(name: String, email: String): Boolean {
        val userData = UserData(name, email)
        localDataStore.setUser(userData)

        return localDataStore.getUser().firstOrNull() == userData
    }
}
