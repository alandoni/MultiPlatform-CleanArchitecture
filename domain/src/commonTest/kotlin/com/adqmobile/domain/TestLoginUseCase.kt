package com.adqmobile.domain

import com.adqmobile.domain.entities.LoginRequestEntity
import kotlin.test.*

class TestLoginUseCase {

    @Test
    fun testRequestAndStoreUser() {
        val response = TestUtils.createLoginUseCase().execute(LoginRequestEntity("test@test.com", "123123"))
        assertNotNull(response)
    }

    @Test
    fun testRequestWithEmptyEmail() {
        try {
            TestUtils.createLoginUseCase().execute(LoginRequestEntity("", "123123"))
        } catch (error: ValidationException) {
            assertEquals("Email required", error.message)
        }
    }

    @Test
    fun testRequestWithInvalidEmail() {
        try {
            TestUtils.createLoginUseCase().execute(LoginRequestEntity("asd", "123123"))
        } catch (error: ValidationException) {
            assertEquals("Invalid email", error.message)
        }
    }

    @Test
    fun testRequestWithWrongPassword() {
        try {
            TestUtils.createLoginUseCase().execute(LoginRequestEntity("asd@asd", "123"))
        } catch (error: ValidationException) {
            assertEquals("Invalid Password", error.message)
        }
    }
}