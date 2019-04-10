package com.adqmobile.cleanarchitecture.data

import android.content.Context
import com.adqmobile.domain.entities.LoginRequestEntity
import com.adqmobile.domain.entities.UserEntity
import com.adqmobile.domain.repositories.user.GetUserApi
import com.adqmobile.domain.repositories.user.UserLocalRepository
import com.adqmobile.domain.repositories.user.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(val context: Context) : UserRepository {

    override fun getByEmail(email: String): UserEntity? {

        val loginRequestEntity = LoginRequestEntity(email, "")
        val api = GetUserApi(loginRequestEntity)
        var userEntity = Request<LoginRequestEntity, UserEntity>(context).request(api, UserEntity::class.java)

        return if (userEntity != null) {
            val db = DatabaseHandler(context)
            db.onCreate(db.writableDatabase)

            db.writableDatabase.execSQL(
                UserLocalRepository().insert(),
                arrayOf(userEntity.name, userEntity.email, userEntity.password)
            )

            val cursorInsert = db.readableDatabase.rawQuery("select last_insert_rowid()", null)
            cursorInsert!!.moveToNext()
            val id = cursorInsert.getLong(0)
            cursorInsert.close()

            val cursor = db.readableDatabase.rawQuery(
                UserLocalRepository().selectByID(),
                arrayOf(id.toString())
            )

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