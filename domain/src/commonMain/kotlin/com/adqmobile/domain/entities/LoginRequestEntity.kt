package com.adqmobile.domain.entities

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class LoginRequestEntity(
    var email: String,
    var password: String?
): BaseEntity {
    override fun toString(): String {
        return Json.stringify(serializer(), this)
    }

    companion object {
        fun fromJson(json: String): LoginRequestEntity {
            return Json.parse(serializer(), json)
        }
    }
}
