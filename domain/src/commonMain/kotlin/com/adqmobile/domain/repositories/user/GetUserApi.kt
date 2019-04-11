package com.adqmobile.domain.repositories.user

import com.adqmobile.domain.entities.LoginRequestEntity
import com.adqmobile.domain.entities.UserEntity
import com.adqmobile.domain.repositories.HttpMethod
import com.adqmobile.domain.repositories.Api
import com.adqmobile.domain.repositories.Request
import com.adqmobile.domain.repositories.Requester

class GetUserApi(private val loginRequest: LoginRequestEntity,
                 private var request: Request): Api(), Requester<UserEntity> {
    override fun getUrl(): String {
        return "users"
    }

    override fun getBody(): String {
        return this.loginRequest.toString()
    }

    override fun getMethod(): HttpMethod {
        return HttpMethod.POST
    }

    override fun execute(): String? {
        return request.execute(this)
    }

    override fun request(): UserEntity? {
        val response = execute()
        return if (response != null) {
            UserEntity.fromJson(response)
        } else {
            null
        }
    }
}