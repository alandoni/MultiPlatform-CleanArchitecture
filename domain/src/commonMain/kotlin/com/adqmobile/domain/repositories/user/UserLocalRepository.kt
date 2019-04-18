package com.adqmobile.domain.repositories.user

import com.adqmobile.domain.entities.UserEntity
import com.adqmobile.domain.repositories.BaseLocalRepository

interface UserLocalRepository: BaseLocalRepository {
    fun selectAll(): List<UserEntity>?

    fun insert(userEntity: UserEntity): Int

    fun selectByID(id: Int): UserEntity?

    fun delete(id: Int): Int
}