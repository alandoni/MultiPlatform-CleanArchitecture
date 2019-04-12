package com.adqmobile.domain.repositories

interface BaseLocalRepository: BaseRepository {
    fun createTableQuery(): String
}