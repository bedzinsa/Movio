package com.arunasbedzinskas.movio.domain.usecase

import com.arunasbedzinskas.movio.domain.base.BaseUnitTest
import com.arunasbedzinskas.movio.domain.mock.LocalDataStoreMock
import com.arunasbedzinskas.movio.models.data.UserData
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SaveUserDataUseCaseUnitTest : BaseUnitTest() {

    @Test
    fun `given save successful when invoke then true result returned`() {
        runTest {
            // Given
            val user = UserData("Test", "test@tester.com")
            val localDataStore = LocalDataStoreMock.mockForUserData(user)
            val useCase = SaveUserDataUseCaseImpl(localDataStore)

            // When
            val isSuccessful = useCase(user.name, user.email)

            // Then
            assertTrue(isSuccessful)
        }
    }

    @Test
    fun `given save failure when invoke then false result returned`() {
        runTest {
            // Given
            val user = UserData("Test", "test@tester.com")
            val localDataStore = LocalDataStoreMock.mockForUserData(storedUser = null)
            val useCase = SaveUserDataUseCaseImpl(localDataStore)

            // When
            val isSuccessful = useCase(user.name, user.email)

            // Then
            assertFalse(isSuccessful)
        }
    }
}
