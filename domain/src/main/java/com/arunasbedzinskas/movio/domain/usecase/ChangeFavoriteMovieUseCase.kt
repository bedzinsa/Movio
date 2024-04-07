package com.arunasbedzinskas.movio.domain.usecase

import com.arunasbedzinskas.movio.data.datastore.LocalDataStore

fun interface ChangeFavoriteMovieUseCase {

    suspend operator fun invoke(id: Long, isFavorite: Boolean)
}

internal class ChangeFavoriteMovieUseCaseImpl(
    private val localDataStore: LocalDataStore
) : ChangeFavoriteMovieUseCase {

    override suspend fun invoke(
        id: Long,
        isFavorite: Boolean
    ) = if (isFavorite) {
        localDataStore.addFavorite(id)
    } else {
        localDataStore.removeFavorite(id)
    }
}
