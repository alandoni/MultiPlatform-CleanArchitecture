package com.adqmobile.domain.repositories

import com.adqmobile.domain.repositories.user.UserLocalRepository

class DatabaseInitializer(private val database: AbstractDatabase) {

    fun onCreate(): Array<String> {
        return arrayOf(UserLocalRepository(database).createTableQuery())
    }

    fun onUpgrade(oldVersion: Int, newVersion: Int): Array<String>? {
        return null
    }

    fun getDatabaseVersion(): Int {
        val map = database.executeSelectQuery("PRAGMA user_version")!![0]
        return map["user_version"]!!.toInt()
    }

    fun setDatabaseVersion(version: Int) {
        database.runStatement("PRAGMA user_version = $version")
    }
}