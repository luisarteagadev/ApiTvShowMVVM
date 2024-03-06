package com.example.apitvshowmvvm.core.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apitvshowmvvm.R
import com.example.apitvshowmvvm.core.interfaces.RecyclerViewClickListener
import com.example.apitvshowmvvm.data.models.TVShow
import com.squareup.picasso.Picasso

class TVShowMostPopularAdapter(private var listTVShows:MutableList<TVShow>,private val clickListener: RecyclerViewClickListener):RecyclerView.Adapter<TVShowMostPopularAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),View.OnClickListener {
        val tvName:TextView=itemView.findViewById(R.id.tvName)
        val tvNetwork:TextView=itemView.findViewById(R.id.tvNetWork)
        val tvStarted:TextView=itemView.findViewById(R.id.tvStarted)
        val tvStatus:TextView=itemView.findViewById(R.id.tvStatus)
        var ivTVShow:com.makeramen.roundedimageview.RoundedImageView=itemView.findViewById(R.id.ivTVShow)
        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                // Llamar al método del click listener
                clickListener.onRecyclerViewItemClick(listTVShows[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_container_tv_show,parent,false))
    }

    override fun getItemCount(): Int {

        return listTVShows.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemTVShow=listTVShows[position]
        holder.tvName.text=itemTVShow.name
        holder.tvNetwork.text=itemTVShow.network
        holder.tvStarted.text=itemTVShow.starDate
        holder.tvStatus.text=itemTVShow.status
        Picasso.get().load(itemTVShow.thumbnail).into(holder.ivTVShow);
    }
    fun updateTVShows(newTVShows: MutableList<TVShow>) {
        val positionStart=this.listTVShows.size
        Log.i("tagUpdate",positionStart.toString())
        this.listTVShows.addAll(newTVShows)
        notifyItemRangeInserted(positionStart, newTVShows.size)


    }
    fun clear(){
        this.listTVShows.clear()
    }
}