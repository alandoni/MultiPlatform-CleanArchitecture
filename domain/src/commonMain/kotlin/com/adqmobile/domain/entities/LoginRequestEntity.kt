package com.adqmobile.domain.entities

data class LoginRequestEntity(
    var email: String,
    var password: String?
): IEntity
