package com.arunasbedzinskas.movio.domain.usecase

import com.arunasbedzinskas.movio.data.datastore.LocalDataStore
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

fun interface GetIsAccountCreatedUseCase {

    suspend operator fun invoke(): Boolean
}

internal class GetIsAccountCreatedUseCaseImpl @Inject constructor(
    private val dataStore: LocalDataStore
) : GetIsAccountCreatedUseCase {

    override suspend fun invoke(): Boolean = dataStore.getUser().firstOrNull() != null
}
