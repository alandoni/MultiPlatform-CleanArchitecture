package com.adqmobile.data.user

import com.adqmobile.domain.entities.UserEntity
import com.adqmobile.data.base.AbstractDatabase
import com.adqmobile.domain.repositories.UserLocalRepository

class UserLocalRepositoryImpl(private val database: AbstractDatabase): UserLocalRepository {
    override fun createTableQuery(): String {
        return "CREATE TABLE IF NOT EXISTS `users` (" +
                "`id` INTEGER PRIMARY KEY, " +
                "`name` TEXT, " +
                "`email` TEXT, " +
                "`password` TEXT);"
    }

    override fun selectAll(): List<UserEntity>? {
        val list = database.read("SELECT * FROM `users`")
        return convert(list)
    }

    override fun insert(userEntity: UserEntity): Int {
        return database.write("INSERT INTO `users` (name, email, password) VALUES (?, ?, ?);",
            userEntity.name, userEntity.email, userEntity.password)
    }

    override fun selectByID(id: Int): UserEntity? {
        val list = database.read("SELECT * FROM `users` WHERE `users`.`id` = ?;", id.toString())
        val users = convert(list) ?: return null
        return users[0]
    }

    override fun delete(id: Int): Int {
        return database.write("DELETE FROM `users` WHERE `users`.`id` = ?;", id.toString())
    }

    private fun convert(list: List<Map<String, String?>>?): List<UserEntity>? {
        if (list == null) {
            return null
        }
        val newList = mutableListOf<UserEntity>()
        for (userMap: Map<String, String?> in list) {
            newList.add(UserEntity.convert(userMap))
        }
        return newList
    }
}