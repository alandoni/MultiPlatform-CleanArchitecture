package com.adqmobile.domain.repositories.user

import com.adqmobile.domain.entities.LoginRequestEntity
import com.adqmobile.domain.repositories.HttpMethod
import com.adqmobile.domain.repositories.IApi

class GetUserApi(private var loginRequest: LoginRequestEntity) : IApi<LoginRequestEntity> {
    override fun getUrl(): String {
        return "users"
    }

    override fun getBody(): LoginRequestEntity {
        return this.loginRequest
    }

    override fun getMethod(): HttpMethod {
        return HttpMethod.GET
    }
}