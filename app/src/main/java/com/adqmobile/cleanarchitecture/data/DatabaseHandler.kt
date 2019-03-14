package com.adqmobile.cleanarchitecture.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.adqmobile.domain.repositories.user.UserInfoBD
import javax.inject.Inject

class DatabaseHandler @Inject constructor(context: Context) : SQLiteOpenHelper(context, DatabaseHandler.DB_NAME, null, DatabaseHandler.DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = UserInfoBD().createTable()
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS `users`"
        db.execSQL(DROP_TABLE)
        onCreate(db)
    }

    companion object {
        private val DB_VERSION = 1
        private val DB_NAME = "db.db"
    }
}