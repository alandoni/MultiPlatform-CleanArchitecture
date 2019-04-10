package com.adqmobile.domain.repositories

import com.adqmobile.domain.Throws

abstract class AbstractDatabase @Throws constructor() {

    abstract fun executeSelectQuery(sql: String, vararg params: Any?): List<Map<String, Any?>>?

    abstract fun runStatement(sql: String, vararg params: Any?): Int
}