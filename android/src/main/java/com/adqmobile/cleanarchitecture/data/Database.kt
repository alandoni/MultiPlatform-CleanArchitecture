package com.adqmobile.cleanarchitecture.data

import android.content.Context
import com.adqmobile.domain.repositories.AbstractDatabase
import com.adqmobile.domain.repositories.DatabaseInitializer
import javax.inject.Inject

class Database @Inject constructor(context: Context) : AbstractDatabase() {

    private val db: DatabaseHandler = DatabaseHandler(context, DatabaseInitializer(this))

    override fun executeSelectQuery(sql: String, vararg params: String?): List<Map<String, String?>>? {
        val cursor = db.readableDatabase.rawQuery(sql, params)

        var list: ArrayList<Map<String, String?>> = ArrayList()
        while (cursor.moveToNext()) {
            var value: HashMap<String, String?> = HashMap()
            for (column in cursor.columnNames) {
                value[column] = cursor.getString(cursor.getColumnIndex(column))
            }
            list.add(value)
        }
        cursor.close()
        db.close()
        return list
    }

    override fun runStatement(sql: String, vararg params: String?): Int {
        db.writableDatabase.execSQL(sql, params)
        val cursorInsert = db.readableDatabase.rawQuery("select last_insert_rowid()", null)
        cursorInsert!!.moveToNext()
        val id = cursorInsert.getLong(0)
        cursorInsert.close()
        db.close()
        return id.toInt()
    }
}