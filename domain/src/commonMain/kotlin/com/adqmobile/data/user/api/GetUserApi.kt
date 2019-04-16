package com.adqmobile.data.user

import com.adqmobile.domain.entities.LoginRequestEntity
import com.adqmobile.domain.entities.UserEntity
import com.adqmobile.data.base.HttpMethod
import com.adqmobile.data.base.BaseApi
import com.adqmobile.data.base.BaseRequest
import com.adqmobile.data.base.TypedRequest

class GetUserApi(private val loginRequest: LoginRequestEntity,
                 private var request: BaseRequest
): BaseApi(), TypedRequest<UserEntity> {

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
        if (request.error != null) {
            throw request.error!!
        }
        return if (response != null) {
            UserEntity.fromJson(response)
        } else {
            null
        }
    }
}