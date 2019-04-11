package com.adqmobile.domain.repositories.user

import com.adqmobile.domain.entities.UserEntity
import com.adqmobile.domain.repositories.AbstractDatabase

class UserLocalRepository(private val database: AbstractDatabase) {
    fun createTable() {
        database.runStatement("CREATE TABLE IF NOT EXISTS `users` (" +
                "`id` INTEGER PRIMARY KEY," +
                "`name` TEXT," +
                "`email` TEXT," +
                "`password` TEXT);")
    }

    fun selectAll(): List<UserEntity>? {
        val list = database.executeSelectQuery("SELECT * FROM `users`")
        return convert(list)
    }

    fun insert(userEntity: UserEntity): Int {
        return database.runStatement("INSERT INTO `users` (name, email, password) VALUES (?, ?, ?);",
            userEntity.name, userEntity.email, userEntity.password)
    }

    fun selectByID(id: Int): UserEntity? {
        val list = database.executeSelectQuery("SELECT * FROM `users` WHERE `users`.`id` = ?;", id.toString())
        val users = convert(list) ?: return null
        return users[0]
    }

    fun delete(id: Int): Int {
        return database.runStatement("DELETE FROM `users` WHERE `users`.`id` = ?;", id.toString())
    }

    fun convert(list: List<Map<String, String?>>?): List<UserEntity>? {
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