package com.work.asinghi.marvelbooking.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.work.asinghi.marvelbooking.PicassoTrustAll
import com.work.asinghi.marvelbooking.R
import com.work.asinghi.marvelbooking.model.Locations

class CustomAdapter(val context: Context, val locationList: List<Locations>, val listener: OnItemClickListener) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val location: Locations = locationList[position]
        holder.bind(locationList.get(position), listener);

        holder.date?.text = location.date
        holder.myImageViewText.text = location.place
        PicassoTrustAll.getInstance(context).load(location.url).fit().into(holder.image)
        if (location.like)
            holder.heart.setImageResource(R.drawable.heart_icon_filled)
        else
            holder.heart.setImageResource(R.drawable.heart_icon)

    }

    override fun getItemCount(): Int {
        return locationList.size
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, item: Locations, position: Int)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Locations, listener: OnItemClickListener) {
            itemView.setOnClickListener {
                listener.onItemClick(itemView, item, adapterPosition) }
        }

        val date = itemView.findViewById<TextView>(R.id.date)
        val image = itemView.findViewById<ImageView>(R.id.image)
        val heart = itemView.findViewById<ImageView>(R.id.heart)
        val myImageViewText = itemView.findViewById<TextView>(R.id.myImageViewText)

    }
}
