package com.adqmobile.domain.entities

interface Entity {
    fun fromJson(json: String): Entity
    override fun toString(): String
}
