package com.adqmobile.domain.entities

data class UserEntity(
    var name : String,
    var email : String,
    var password : String
) : IEntity {

    override fun toString(): String {
        return "{name: ".plus(name).plus(", email: ").plus(email).plus(", password: ").plus(password).plus("}")
    }
}