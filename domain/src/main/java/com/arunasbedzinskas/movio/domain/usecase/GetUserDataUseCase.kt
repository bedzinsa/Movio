package com.arunasbedzinskas.movio.domain.usecase

import com.arunasbedzinskas.movio.data.datastore.LocalDataStore
import com.arunasbedzinskas.movio.models.ui.UserDataUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

fun interface GetUserDataUseCase {

    operator fun invoke(): Flow<UserDataUI>
}

internal class GetUserDataUseCaseImpl(
    private val localDataStore: LocalDataStore
) : GetUserDataUseCase {

    override fun invoke(): Flow<UserDataUI> =
        localDataStore.getUser().transform { userData ->
            userData?.let {
                emit(UserDataUI(userData.name, userData.avatar))
            }
        }
}
