package com.kienct.sqlitedemo.adapter

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import com.kienct.sqlitedemo.R
import com.kienct.sqlitedemo.activities.CreateActivity
import com.kienct.sqlitedemo.activities.UpdateActivity
import kotlinx.android.synthetic.main.choice_layout.view.*
import kotlinx.android.synthetic.main.item_layout.view.*

class ImageAdapter(private val imgSrc: MutableList<Int>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.choice_layout, parent, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imgSrc.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ImageViewHolder) {
            holder.bind(imgSrc[position])
            holder.itemView.setOnClickListener { v ->
                val intent = Intent("send src")
                intent.putExtra("imgSrc", imgSrc[position])
                LocalBroadcastManager.getInstance(v.context).sendBroadcast(intent)
            }
        }
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(src: Int) {
            itemView.src.setImageResource(src)
        }
    }
}