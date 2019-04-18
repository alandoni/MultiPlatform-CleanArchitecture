package com.adqmobile.data.user

import com.adqmobile.domain.entities.LoginRequestEntity
import com.adqmobile.domain.entities.UserEntity
import com.adqmobile.data.base.BaseRequest
import com.adqmobile.domain.repositories.user.UserRemoteRepository

class UserRemoteRepositoryImpl(private val request: BaseRequest): UserRemoteRepository {
    override fun getByEmail(login: LoginRequestEntity): UserEntity? {
        return GetUserApi(login, request).request()
    }
}