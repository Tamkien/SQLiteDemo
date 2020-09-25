package com.kienct.sqlitedemo.db

import android.content.Context
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBManager(
    context: Context?,
    name: String? = DATABASE_NAME,
    factory: SQLiteDatabase.CursorFactory? = null,
    version: Int = 1,
    errorHandler: DatabaseErrorHandler? = null
) : SQLiteOpenHelper(context, name, factory, version, errorHandler) {
    companion object {
        const val DATABASE_NAME = "SECOND"
        const val PRODUCT = "PRODUCT_TABLE"
        const val PRODUCT_NAME = "PRODUCT_NAME"
        const val PRODUCT_PRICE = "PRODUCT_PRICE"
        const val PRODUCT_ID = "ID"
        const val PRODUCT_IMAGE = "PRODUCT_IMAGE"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val createProduct = "CREATE TABLE $PRODUCT (" +
                "$PRODUCT_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$PRODUCT_NAME TEXT, " +
                "$PRODUCT_IMAGE BLOB, " +
                "$PRODUCT_PRICE INTEGER)"
        p0?.execSQL(createProduct)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS $PRODUCT")
    }
}