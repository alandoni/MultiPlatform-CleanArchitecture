package com.adqmobile.domain.repositories.user

import com.adqmobile.domain.entities.UserEntity
import com.adqmobile.domain.repositories.IRepository

interface UserRepository : IRepository {
    fun getByEmail(email : String) : UserEntity?
}