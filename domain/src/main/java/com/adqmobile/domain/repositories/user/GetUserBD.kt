package com.adqmobile.domain.repositories.user

class UserInfoBD {
    fun selectAll() : String {
        return "SELECT * FROM `users`"
    }

    fun insert() : String {
        return "INSERT INTO `users` (name, email, password) VALUES (?, ?, ?)"
    }

    fun selectByID() : String {
        return "SELECT * FROM `users` WHERE `users.id` = ?"
    }

    fun delete() : String {
        return "DELETE FROM `users` WHERE `users.id` = ?"
    }
}