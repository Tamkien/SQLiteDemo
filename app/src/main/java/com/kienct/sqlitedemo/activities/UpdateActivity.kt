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
import com.bumptech.glide.Glide
import com.kienct.sqlitedemo.R
import com.kienct.sqlitedemo.adapter.ImageAdapter
import com.kienct.sqlitedemo.db.ProductDAO
import com.kienct.sqlitedemo.model.Product
import kotlinx.android.synthetic.main.activity_update.*
import java.io.ByteArrayOutputStream

class UpdateActivity : AppCompatActivity() {
    private var image: Int = 0
    val stream = ByteArrayOutputStream()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        Glide.with(this)
            .load("https://raw.githubusercontent.com/bumptech/glide/master/static/glide_logo.png")
            .into(choice2)
        val productName = intent.getStringExtra("productName")
        editingName.setText(productName)
        val productID = intent.getStringExtra("productID")
        val productDAO = ProductDAO(this)
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
        button2.setOnClickListener {
            if (editingName.text.toString() != "" && editingPrice.text.toString() != ""
            ) {
                if (productID != null) {
                    productDAO.update(
                        Product(
                            productID.toInt(),
                            editingName.text.toString(),
                            editingPrice.text.toString().toInt(),
                            stream.toByteArray()
                        )
                    )
                }
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