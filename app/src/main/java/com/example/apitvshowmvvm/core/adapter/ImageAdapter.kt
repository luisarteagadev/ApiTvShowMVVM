package com.example.apitvshowmvvm.core.adapter

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.apitvshowmvvm.R
import com.squareup.picasso.Picasso

class ImageAdapter(private val imageList:ArrayList<String>)
    :RecyclerView.Adapter<ImageAdapter.ImageViewHolder>(){

    class ImageViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val imageView:ImageView=itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_container_slider_image,parent,false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int {
       return  imageList.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageUrl = imageList[position]
        Picasso.get().load(imageUrl).into(holder.imageView)
    }
}