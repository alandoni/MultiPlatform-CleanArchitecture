package com.adqmobile.domain.entities

data class LoginResponseEntity(
    var success: Boolean,
    var error: String?
) : IEntity