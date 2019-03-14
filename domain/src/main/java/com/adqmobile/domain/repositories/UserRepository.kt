package com.adqmobile.domain.repositories

import com.adqmobile.domain.entities.UserEntity

interface UserRepository : IRepository {
    fun getByEmail(email : String) : UserEntity?
}