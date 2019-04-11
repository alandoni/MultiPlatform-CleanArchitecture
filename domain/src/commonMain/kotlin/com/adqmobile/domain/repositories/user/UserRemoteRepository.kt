package com.adqmobile.domain.repositories.user

import com.adqmobile.domain.entities.LoginRequestEntity
import com.adqmobile.domain.entities.UserEntity
import com.adqmobile.domain.repositories.IRepository
import com.adqmobile.domain.repositories.Request

class UserRemoteRepository(private val request: Request<LoginRequestEntity>): IRepository {

    fun getByEmail(login: LoginRequestEntity): UserEntity? {
        val userMap = GetUserApi(login, request).execute()?: return null
        return UserEntity.convert(userMap)
    }
}