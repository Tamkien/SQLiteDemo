package com.kienct.sqlitedemo.adapter

import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.kienct.sqlitedemo.R
import com.kienct.sqlitedemo.activities.DeleteActivity
import com.kienct.sqlitedemo.activities.UpdateActivity
import com.kienct.sqlitedemo.model.Product
import kotlinx.android.synthetic.main.item_layout.view.*


class ProductAdapter(private val products: MutableList<Product>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProductViewHolder) {
            holder.bind(products[position])
            holder.itemView.findViewById<ImageView>(R.id.buttonUpdate).setOnClickListener { v ->
                Log.d("Updating", position.toString())
                val intent = Intent(v.context, UpdateActivity()::class.java)
                intent.putExtra("productName", holder.itemView.name.text.toString())
                intent.putExtra("productID", holder.itemView.product_id.text.toString())
                v.context.startActivity(intent)
            }
            holder.itemView.findViewById<ImageView>(R.id.buttonDelete).setOnClickListener { v ->
                Log.d("Deleting", position.toString())
                val intent = Intent(v.context, DeleteActivity()::class.java)
                intent.putExtra("productName", holder.itemView.name.text.toString())
                intent.putExtra("productID", holder.itemView.product_id.text.toString())
                v.context.startActivity(intent)
            }
        }
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(product: Product) {
            itemView.name.text = product.name
            itemView.price.text = product.price.toString()
            itemView.product_id.text = product.id.toString()
            val bitmap =
                product.imgSrc?.size?.let { BitmapFactory.decodeByteArray(product.imgSrc, 0, it) }
            itemView.img.setImageBitmap(bitmap)
        }
    }


}