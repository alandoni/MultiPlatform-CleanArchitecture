package com.adqmobile.domain.entities

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class UserEntity(
    var name: String,
    var email: String,
    var password: String
): Entity() {

    override fun toString(): String {
        return Json.stringify(serializer(), this)
    }

    companion object {
        fun convert(map: Map<String, String?>): UserEntity {
            return UserEntity(
                map["name"]!!,
                map["email"]!!,
                map["password"]!!
            )
        }

        fun fromJson(json: String): UserEntity {
            return Json.parse(serializer(), json)
        }
    }
}