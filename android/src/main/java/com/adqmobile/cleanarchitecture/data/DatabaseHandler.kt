package com.adqmobile.cleanarchitecture.data

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.adqmobile.data.base.DatabaseInitializer

class DatabaseHandler constructor(
    context: Context,
    private val databaseInitializer: DatabaseInitializer
): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val queries = databaseInitializer.onCreate()
        for (query in queries) {
            db.execSQL(query)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val queries = databaseInitializer.onUpgrade(oldVersion, newVersion)
        if (queries != null) {
            for (query in queries) {
                db.execSQL(query)
            }
        }
    }

    companion object {
        private val DB_VERSION = 1
        private val DB_NAME = "db.db"

        fun requestPermissionIfNeeded(activity: Activity): Boolean {
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                return ActivityCompat.shouldShowRequestPermissionRationale(activity,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
            return true
        }
    }
}