package com.adqmobile.domain.usecases

import com.adqmobile.domain.entities.LoginRequestEntity
import com.adqmobile.domain.entities.LoginResponseEntity
import com.adqmobile.domain.ValidationException
import com.adqmobile.domain.repositories.UserLocalRepository
import com.adqmobile.domain.repositories.user.UserRemoteRepository

class LoginUseCase constructor(
    private val localRepository: UserLocalRepository,
    private val remoteRepository: UserRemoteRepository
): BaseUseCase<LoginRequestEntity, LoginResponseEntity> {

    private fun isEmptyOrNull(string: String?): Boolean {
        return string == null || string.isEmpty()
    }

    private fun isEmailValid(email: String): Boolean {
        for (i in 0..email.length - 1) {
            if (email[i] == '@') {
                return true
            }
        }
        return false
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 4
    }

    override fun execute(param: LoginRequestEntity): LoginResponseEntity {
        // Check for a valid password, if the user entered one.
        if (isEmptyOrNull(param.password) || !isPasswordValid(param.password!!)) {
            throw ValidationException("Invalid Password")
        }

        // Check for a valid email address.
        if (isEmptyOrNull(param.email)) {
            throw ValidationException("Email required")
        } else if (!isEmailValid(param.email)) {
            throw ValidationException("Invalid email")
        }

        try {
            val entity = remoteRepository.getByEmail(param)
            return if (entity != null) {
                val id = localRepository.insert(entity)
                val entityNew = localRepository.selectByID(id)
                LoginResponseEntity(true, entityNew.toString())
            } else {
                LoginResponseEntity(false, "Invalid email")
            }
        } catch (e: Throwable) {
            throw e
        }
    }
}