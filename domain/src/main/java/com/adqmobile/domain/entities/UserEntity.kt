package com.adqmobile.domain.entities

data class UserEntity(
    var name : String,
    var email : String,
    var password : String
) : IEntity