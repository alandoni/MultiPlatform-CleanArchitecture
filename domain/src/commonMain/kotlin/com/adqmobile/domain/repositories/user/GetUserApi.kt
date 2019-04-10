package com.adqmobile.domain.repositories.user

import com.adqmobile.domain.entities.LoginRequestEntity
import com.adqmobile.domain.entities.LoginResponseEntity
import com.adqmobile.domain.entities.UserEntity
import com.adqmobile.domain.repositories.HttpMethod
import com.adqmobile.domain.repositories.IApi
import com.adqmobile.domain.repositories.Request

class GetUserApi(private val loginRequest: LoginRequestEntity,
                 private var request: Request<LoginRequestEntity>): IApi<LoginRequestEntity> {
    override fun getUrl(): String {
        return "users"
    }

    override fun getBody(): LoginRequestEntity {
        return this.loginRequest
    }

    override fun getMethod(): HttpMethod {
        return HttpMethod.GET
    }

    override fun execute(): Map<String, Any?>? {
        return request.execute(this)
    }
}