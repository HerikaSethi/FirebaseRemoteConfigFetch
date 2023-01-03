package com.demoproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demoproject.R
import com.demoproject.model.PicSumResponseItem

class EachRowAdapter(val context: Context,val imageItemList: List<PicSumResponseItem>): RecyclerView.Adapter<EachRowAdapter.EachRowViewHolder>() {
 class EachRowViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
     var picSumImage = itemView.findViewById<ImageView>(R.id.Image)
 }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EachRowViewHolder {
        return EachRowViewHolder(LayoutInflater.from(context).inflate(R.layout.picsum_sub_item, parent, false))
    }

    override fun onBindViewHolder(holder: EachRowViewHolder, position: Int) {
      val imageItem = imageItemList[position]
        Glide.with(context).load(imageItem.download_url).into(holder.picSumImage)

    }

    override fun getItemCount(): Int {
       return imageItemList.size
    }

}