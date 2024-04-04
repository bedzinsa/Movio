package com.arunasbedzinskas.movio.domain.usecase

import com.arunasbedzinskas.movio.domain.base.BaseUnitTest
import com.arunasbedzinskas.movio.domain.mock.LocalDataStoreMock
import com.arunasbedzinskas.movio.models.data.UserData
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class GetIsAccountCreatedUseCaseUnitTest : BaseUnitTest() {

    @Test
    fun `given user exists when invoke then true result returned`() {
        runTest {
            // Given
            val user = UserData("Test", "test@tester.com")
            val localDataStore = LocalDataStoreMock.mockForUserData(user)
            val useCase = GetIsAccountCreatedUseCaseImpl(localDataStore)

            // When
            val isCreated = useCase()

            // Then
            Assert.assertTrue(isCreated)
        }
    }

    @Test
    fun `given user does not exist when invoke then false result returned`() {
        runTest {
            // Given
            val localDataStore = LocalDataStoreMock.mockForUserData(storedUser = null)
            val useCase = GetIsAccountCreatedUseCaseImpl(localDataStore)

            // When
            val isCreated = useCase()

            // Then
            Assert.assertFalse(isCreated)
        }
    }
}
