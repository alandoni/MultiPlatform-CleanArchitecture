package com.adqmobile.data.user

import com.adqmobile.domain.entities.LoginRequestEntity
import com.adqmobile.domain.entities.UserEntity
import com.adqmobile.domain.repositories.BaseRepository
import com.adqmobile.data.base.BaseRequest

class UserRemoteRepository(private val request: BaseRequest): BaseRepository {
    fun getByEmail(login: LoginRequestEntity): UserEntity? {
        return GetUserApi(login, request).request()
    }
}