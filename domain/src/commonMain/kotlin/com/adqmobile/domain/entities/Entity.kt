package com.adqmobile.domain.entities

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
abstract class Entity {
    abstract override fun toString(): String

    companion object {
        fun fromJson(json: String): Entity {
            return Json.parse(serializer(), json)
        }
    }
}
