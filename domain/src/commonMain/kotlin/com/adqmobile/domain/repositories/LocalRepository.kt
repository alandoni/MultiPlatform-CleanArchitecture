package com.adqmobile.domain.repositories

interface LocalRepository {
    fun createTableQuery(): String
}