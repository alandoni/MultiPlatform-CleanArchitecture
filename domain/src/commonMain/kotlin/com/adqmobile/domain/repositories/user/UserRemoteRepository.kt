package com.adqmobile.domain.repositories.user

import com.adqmobile.domain.entities.LoginRequestEntity
import com.adqmobile.domain.entities.UserEntity
import com.adqmobile.domain.repositories.BaseRepository

interface UserRemoteRepository: BaseRepository {
    fun getByEmail(login: LoginRequestEntity): UserEntity?
}