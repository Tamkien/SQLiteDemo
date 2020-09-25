package com.kienct.sqlitedemo.activities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kienct.sqlitedemo.R
import com.kienct.sqlitedemo.adapter.ImageAdapter
import com.kienct.sqlitedemo.db.ProductDAO
import com.kienct.sqlitedemo.model.Product
import kotlinx.android.synthetic.main.activity_create.*
import java.io.ByteArrayOutputStream


class CreateActivity : AppCompatActivity() {
    private lateinit var productDAO: ProductDAO
    private var image: Int = 0
    val stream = ByteArrayOutputStream()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        productDAO = ProductDAO(this)
        LocalBroadcastManager.getInstance(this).registerReceiver(
            mMessageReceiver, IntentFilter("send src")
        )

        val imgSrc: MutableList<Int> = ArrayList()
        imgSrc.add(R.drawable.i1)
        imgSrc.add(R.drawable.i2)
        imgSrc.add(R.drawable.i3)
        imgSrc.add(R.drawable.i4)
        imgSrc.add(R.drawable.i5)
        imgSrc.add(R.drawable.i6)
        val imageAdapter = ImageAdapter(imgSrc)

        imageChoicesContainer.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        imageChoicesContainer.adapter = imageAdapter
        button.setOnClickListener {
            if (etCreateName.text.toString() != "" && etCreatePrice.text.toString() != ""
            ) {
                productDAO.insert(
                    Product(
                        null,
                        etCreateName.text.toString(),
                        etCreatePrice.text.toString().toInt(),
                        stream.toByteArray()
                    )
                )
                val intent = Intent(this, ReadActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private var mMessageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            image = intent.getIntExtra("imgSrc", 0)
            choice2.setImageResource(image)
            val bitmap = (choice2.drawable as BitmapDrawable).bitmap
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream)
        }
    }
}