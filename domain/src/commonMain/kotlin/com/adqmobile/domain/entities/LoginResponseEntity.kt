package com.adqmobile.domain.entities

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class LoginResponseEntity(
    var success: Boolean,
    var error: String?
): Entity() {
    override fun toString(): String {
        return Json.stringify(serializer(), this)
    }

    companion object {
        fun fromJson(json: String): LoginResponseEntity {
            return Json.parse(serializer(), json)
        }
    }
}