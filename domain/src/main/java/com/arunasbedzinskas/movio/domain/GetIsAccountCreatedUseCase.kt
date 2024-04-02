package com.arunasbedzinskas.movio.domain

import com.arunasbedzinskas.movio.data.datastore.LocalDataStore
import javax.inject.Inject

interface GetIsAccountCreatedUseCase {

}

internal class GetIsAccountCreatedUseCaseImpl @Inject constructor(
    private val dataStore: LocalDataStore
) : GetIsAccountCreatedUseCase {

    init {

    }
}
