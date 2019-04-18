package com.adqmobile.domain.repositories

import com.adqmobile.domain.entities.UserEntity

interface UserLocalRepository: BaseLocalRepository {
    fun selectAll(): List<UserEntity>?

    fun insert(userEntity: UserEntity): Int

    fun selectByID(id: Int): UserEntity?

    fun delete(id: Int): Int
}