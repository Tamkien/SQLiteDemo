package com.kienct.sqlitedemo.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.kienct.sqlitedemo.model.Product

class ProductDAO(context: Context) {
    private var db: SQLiteDatabase

    init {
        val dbManager = DBManager(context)
        db = dbManager.writableDatabase
    }

    fun insert(product: Product): Long {
        val values = ContentValues()
        values.put(DBManager.PRODUCT_NAME, product.name)
        values.put(DBManager.PRODUCT_IMAGE, product.imgSrc)
        values.put(DBManager.PRODUCT_PRICE, product.price)
        return db.insert(DBManager.PRODUCT, null, values)
    }

    fun delete(id: String): Int {
        return db.delete(DBManager.PRODUCT, "${DBManager.PRODUCT_ID}=?", arrayOf(id))
    }

    fun update(product: Product): Int {
        val values = ContentValues()
        values.put(DBManager.PRODUCT_PRICE, product.price)
        values.put(DBManager.PRODUCT_IMAGE, product.imgSrc)
        values.put(DBManager.PRODUCT_NAME, product.name)
        return db.update(
            DBManager.PRODUCT,
            values,
            "${DBManager.PRODUCT_ID}=?",
            arrayOf(product.id.toString())
        )
    }

    fun selectAll(): MutableList<Product> {
        val output = arrayListOf<Product>()
        val sql = "select * from ${DBManager.PRODUCT}"
        val cursor = db.rawQuery(sql, null)
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex(DBManager.PRODUCT_ID))
            val name = cursor.getString(cursor.getColumnIndex(DBManager.PRODUCT_NAME))
            val price = cursor.getInt(cursor.getColumnIndex(DBManager.PRODUCT_PRICE))
            val img = cursor.getBlob(cursor.getColumnIndex(DBManager.PRODUCT_IMAGE))
            output.add(Product(id, name, price, img))
        }
        cursor.close()
        return output
    }
}