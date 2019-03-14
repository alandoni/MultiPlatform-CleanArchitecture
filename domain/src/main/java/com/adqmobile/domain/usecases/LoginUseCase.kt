package com.adqmobile.domain.usecases

import com.adqmobile.domain.entities.LoginRequestEntity
import com.adqmobile.domain.entities.LoginResponseEntity
import com.adqmobile.domain.repositories.user.UserRepository
import java.lang.RuntimeException
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: UserRepository) : UseCase<LoginRequestEntity, LoginResponseEntity>() {

    private fun isEmptyOrNull(string : String?) : Boolean {
        return string == null || string.isEmpty()
    }

    private fun isEmailValid(email: String): Boolean {
        return email.contains("@")
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 4
    }

    override fun run(params: LoginRequestEntity): LoginResponseEntity {
        // Check for a valid password, if the user entered one.
        if (isEmptyOrNull(params.password) || !isPasswordValid(params.password)) {
            throw RuntimeException("Invalid Password")
        }

        // Check for a valid email address.
        if (isEmptyOrNull(params.email)) {
            throw RuntimeException("Email required")
        } else if (!isEmailValid(params.email!!)) {
            throw RuntimeException("Invalid email")
        }

        var entity = repository.getByEmail(params.email!!)
        return if (entity != null) {
            if (entity.password == params.password) {
                LoginResponseEntity(true, null)
            } else {
                LoginResponseEntity(true, "Invalid password")
            }
        } else {
            LoginResponseEntity(false, "Invalid email")
        }
    }
}