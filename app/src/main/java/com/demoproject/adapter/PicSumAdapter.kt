package com.demoproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demoproject.R
import com.demoproject.model.PicSumResponseItem
import com.demoproject.model.ShapeList

class PicSumAdapter(private val context: Context, private val shapeList: List<ShapeList>):
    RecyclerView.Adapter<PicSumAdapter.PicSumViewHolder>() {

        class PicSumViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
           var shapeTitle: TextView
            var shapeRecycler: RecyclerView
            init {
                shapeTitle = itemView.findViewById(R.id.picSumTitle)
                shapeRecycler = itemView.findViewById(R.id.shapeRecycler)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicSumViewHolder {
            return PicSumViewHolder(LayoutInflater.from(context).inflate(R.layout.picsum_each_row_heading, parent, false))
    }

    override fun onBindViewHolder(holder: PicSumViewHolder, position: Int) {
        holder.shapeTitle.text = shapeList[position].shapeTitle
        setEachShapeItemRecycler(holder.shapeRecycler, shapeList[position].eachImageRow)
    }

    override fun getItemCount(): Int {
        return shapeList.size
    }

    private fun setEachShapeItemRecycler(recyclerView: RecyclerView, eachRowItem: List<PicSumResponseItem>){
              val eachRowRecyclerAdapter = EachRowAdapter(context, eachRowItem)
              recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
              recyclerView.adapter = eachRowRecyclerAdapter
    }
}

