package com.adqmobile.domain.entities

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class LoginRequestEntity(
    var email: String,
    var password: String?
): Entity {
    override fun toString(): String {
        return Json.stringify(LoginRequestEntity.serializer(), this)
    }

    override fun fromJson(json: String): Entity {
        return Json.parse(LoginRequestEntity.serializer(), json)
    }
}
