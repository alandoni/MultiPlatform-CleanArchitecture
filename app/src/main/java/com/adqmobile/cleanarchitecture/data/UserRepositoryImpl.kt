package com.adqmobile.cleanarchitecture.data

import com.adqmobile.domain.entities.LoginRequestEntity
import com.adqmobile.domain.entities.UserEntity
import com.adqmobile.domain.repositories.user.GetUserApi
import com.adqmobile.domain.repositories.user.UserInfoBD
import com.adqmobile.domain.repositories.user.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor() : UserRepository {

    override fun getByEmail(email: String): UserEntity? {
        val loginRequestEntity = LoginRequestEntity(email, "")
        var api = GetUserApi(loginRequestEntity)
        val userEntity = Request<LoginRequestEntity, UserEntity>().request(api, UserEntity::class.java)

        val insertQuery = UserInfoBD().insert()

        val selectQuery = UserInfoBD().selectByID()

        return userEntity
    }
}