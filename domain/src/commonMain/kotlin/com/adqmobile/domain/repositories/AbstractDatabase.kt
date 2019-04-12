package com.adqmobile.domain.repositories

import com.adqmobile.domain.DatabaseException
import com.adqmobile.domain.Throws

abstract class AbstractDatabase @Throws constructor() {

    var error: DatabaseException? = null

    internal fun read(sql: String, vararg params: String?): List<Map<String, String?>>? {
        val result = this.readFromDB(sql, *params)
        if (this.error != null) {
            throw this.error!!
        }
        return result
    }

    internal fun write(sql: String, vararg params: String?): Int {
        val result = this.writeIntoDB(sql, *params)
        if (this.error != null) {
            throw this.error!!
        }
        return result
    }

    abstract fun readFromDB(sql: String, vararg params: String?): List<Map<String, String?>>?

    abstract fun writeIntoDB(sql: String, vararg params: String?): Int

    open fun onError(error: String?) {
        if (error != null) {
            this.error = DatabaseException(error)
        } else {
            this.error = DatabaseException("Unknown database error")
        }
    }
}