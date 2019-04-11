package com.adqmobile.domain.repositories

import com.adqmobile.domain.Throws

abstract class AbstractDatabase @Throws constructor() {
    abstract fun readFromDB(sql: String, vararg params: String?): List<Map<String, String?>>?
    abstract fun writeIntoDB(sql: String, vararg params: String?): Int
}