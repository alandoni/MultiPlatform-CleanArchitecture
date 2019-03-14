package com.adqmobile.cleanarchitecture.data

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
        //val userEntity = Request<LoginRequestEntity, UserEntity>().request(api, UserEntity::class.java)
        val userEntity = UserEntity("Alan", "alan.etm@gmail.com", "123")

        return if (userEntity != null) {
            val insertQuery = UserInfoBD().insert()

            if (ContextCompat.checkSelfPermission(context.applicationContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(context as Activity,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    null
                }
            }

            val db = DatabaseHandler(context)
            val insertCursor = db.writableDatabase.rawQuery(
                insertQuery,
                arrayOf(userEntity!!.name, userEntity!!.email, userEntity!!.password)
            )

            val selectQuery = UserInfoBD().selectByID()
            val cursor = db.readableDatabase.rawQuery(selectQuery, arrayOf(insertCursor.getString(0)))

            userEntity
        } else {
            null
        }
    }
}