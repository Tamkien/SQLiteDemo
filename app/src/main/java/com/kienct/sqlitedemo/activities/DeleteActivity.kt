package com.kienct.sqlitedemo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kienct.sqlitedemo.R
import com.kienct.sqlitedemo.db.ProductDAO
import kotlinx.android.synthetic.main.activity_delete.*

class DeleteActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)
        val productName = intent.getStringExtra("productName")
        textView4.text = productName
        val productID = intent.getStringExtra("productID")
        val productDAO = ProductDAO(this)
        buttonYeah.setOnClickListener{
            if (productID != null) {
                productDAO.delete(productID)
            }
            val intent = Intent(this, ReadActivity::class.java)
            startActivity(intent)
        }
        buttonNah.setOnClickListener{
            val intent = Intent(this, ReadActivity::class.java)
            startActivity(intent)
        }
    }
}