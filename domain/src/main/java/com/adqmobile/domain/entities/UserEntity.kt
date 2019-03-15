package com.adqmobile.domain.entities

data class UserEntity(
    var name : String,
    var email : String,
    var password : String
) : IEntity {

    override fun toString(): String {
        return String.format("{name: %s, email: %s, password: %s}", name, email, password)
    }
}