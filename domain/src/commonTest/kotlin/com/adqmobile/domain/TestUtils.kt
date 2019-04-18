package com.adqmobile.domain

import com.adqmobile.domain.entities.LoginRequestEntity
import com.adqmobile.domain.entities.UserEntity
import com.adqmobile.domain.repositories.UserLocalRepository
import com.adqmobile.domain.repositories.user.UserRemoteRepository
import com.adqmobile.domain.usecases.LoginUseCase

class TestUtils {
    companion object {
        fun createUserLocalRepository(): UserLocalRepository {
            return object: UserLocalRepository {

                private val users = mutableListOf<UserEntity>()

                override fun selectAll(): List<UserEntity>? {
                    return users
                }

                override fun insert(userEntity: UserEntity): Int {
                    users.add(userEntity)
                    return users.size
                }

                override fun selectByID(id: Int): UserEntity? {
                    return users[id - 1]
                }

                override fun delete(id: Int): Int {
                    users.removeAt(id)
                    return id
                }

                override fun createTableQuery(): String {
                    return ""
                }

            }
        }

        fun createUserRemoteRepository(): UserRemoteRepository {
            return object: UserRemoteRepository {
                override fun getByEmail(login: LoginRequestEntity): UserEntity? {
                    return UserEntity(login.email, login.email, "")
                }

            }
        }

        fun createLoginUseCase(): LoginUseCase {
            return LoginUseCase(
                createUserLocalRepository(),
                createUserRemoteRepository()
            )
        }
    }
}