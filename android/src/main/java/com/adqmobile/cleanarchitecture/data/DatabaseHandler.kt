package com.adqmobile.cleanarchitecture.data

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.adqmobile.domain.repositories.user.UserInfoBD
import javax.inject.Inject

class DatabaseHandler @Inject constructor(private val context: Context) : SQLiteOpenHelper(context, DatabaseHandler.DB_NAME, null, DatabaseHandler.DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        if (ContextCompat.checkSelfPermission(context.applicationContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                return
            }
        }
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