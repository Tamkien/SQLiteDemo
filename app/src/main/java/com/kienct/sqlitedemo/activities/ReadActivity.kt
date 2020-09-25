package com.kienct.sqlitedemo.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.kienct.sqlitedemo.R
import com.kienct.sqlitedemo.adapter.ProductAdapter
import com.kienct.sqlitedemo.db.ProductDAO
import kotlinx.android.synthetic.main.activity_read.*
import kotlinx.android.synthetic.main.item_layout.*

class ReadActivity : AppCompatActivity() {
    private lateinit var productDAO: ProductDAO
    private lateinit var productAdapter: ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)
        productDAO = ProductDAO(this)
        productAdapter = ProductAdapter(productDAO.selectAll())
        item_container.layoutManager = LinearLayoutManager(this)
        item_container.adapter = productAdapter
        textView.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}