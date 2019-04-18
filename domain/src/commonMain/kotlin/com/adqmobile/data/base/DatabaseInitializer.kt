package com.adqmobile.data.base

import com.adqmobile.data.user.UserLocalRepositoryImpl

class DatabaseInitializer(private val database: AbstractDatabase) {

    fun onCreate(): Array<String> {
        return arrayOf(UserLocalRepositoryImpl(database).createTableQuery())
    }

    fun onUpgrade(oldVersion: Int, newVersion: Int): Array<String>? {
        return null
    }

    fun getDatabaseVersion(): Int {
        val map = database.readFromDB("PRAGMA user_version")!![0]
        return map["user_version"]!!.toInt()
    }

    fun setDatabaseVersion(version: Int) {
        database.writeIntoDB("PRAGMA user_version = $version")
    }
}