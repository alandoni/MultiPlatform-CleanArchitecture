package com.adqmobile.cleanarchitecture.data

import com.adqmobile.domain.entities.UserEntity
import com.adqmobile.domain.repositories.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor() : UserRepository {

    override fun getByEmail(email: String): UserEntity? {
        return UserEntity("Alan", "alan.etm@gmail.com", "123123")
    }
}