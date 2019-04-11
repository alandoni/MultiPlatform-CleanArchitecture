package com.adqmobile.domain.repositories

import com.adqmobile.domain.repositories.user.UserLocalRepository

class DatabaseInitializer(private val database: AbstractDatabase) {

    fun onCreate() {
        UserLocalRepository(database).createTable()
    }

    fun onUpgrade(oldVersion: Int, newVersion: Int): Boolean {
        return oldVersion != newVersion
    }

    fun getDatabaseVersion(): Int {
        val map = database.executeSelectQuery("PRAGMA user_version")!![0]
        return map["user_version"]!!.toInt()
    }

    fun setDatabaseVersion(version: Int) {
        database.runStatement("PRAGMA user_version = $version")
    }
}