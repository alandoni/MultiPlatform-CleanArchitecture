package com.adqmobile.domain.entities

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class LoginResponseEntity(
    var success: Boolean,
    var error: String?
): Entity {
    override fun toString(): String {
        return Json.stringify(LoginResponseEntity.serializer(), this)
    }

    override fun fromJson(json: String): Entity {
        return Json.parse(LoginResponseEntity.serializer(), json)
    }
}