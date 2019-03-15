package com.adqmobile.cleanarchitecture.data

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.database.sqlite.SQLiteStatement
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.database.sqlite.transaction
import com.adqmobile.domain.entities.LoginRequestEntity
import com.adqmobile.domain.entities.UserEntity
import com.adqmobile.domain.repositories.user.GetUserApi
import com.adqmobile.domain.repositories.user.UserInfoBD
import com.adqmobile.domain.repositories.user.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(val context: Context) : UserRepository {

    override fun getByEmail(email: String): UserEntity? {
        val loginRequestEntity = LoginRequestEntity(email, "")
        var api = GetUserApi(loginRequestEntity)
        var userEntity = Request<LoginRequestEntity, UserEntity>(context).request(api, UserEntity::class.java)
        var cursor : Cursor? = null

        return if (userEntity != null) {
            val db = DatabaseHandler(context)
            val insertQuery = UserInfoBD().insert()

            db.writableDatabase.execSQL(
                insertQuery,
                arrayOf(userEntity!!.name, userEntity!!.email, userEntity!!.password)
            )
            cursor = db.readableDatabase.rawQuery("select last_insert_rowid()", null)
            cursor!!.moveToNext()

            val selectQuery = UserInfoBD().selectByID()
            val cursor = db.readableDatabase.rawQuery(selectQuery, arrayOf(cursor!!.getLong(0).toString()))
            cursor.moveToFirst()
            userEntity = UserEntity(
                cursor.getString(cursor.getColumnIndex("name")),
                cursor.getString(cursor.getColumnIndex("email")),
                cursor.getString(cursor.getColumnIndex("password"))
            )
            cursor.close()
            userEntity
        } else {
            null
        }
    }
}