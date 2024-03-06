package com.example.apitvshowmvvm.core.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apitvshowmvvm.R
import com.example.apitvshowmvvm.data.models.Episode



class EpisodesAdapter(private var lstEpisodes:MutableList<Episode>) :RecyclerView.Adapter<EpisodesAdapter.ViewHolder>() {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val tvTitle:TextView=view.findViewById(R.id.tvTitleItem)
        val tvNameItem:TextView=view.findViewById(R.id.tvNameItem)
        val tvAirDate:TextView=view.findViewById(R.id.tvAirDateItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_container_episode,parent,false))
    }

    override fun getItemCount(): Int {
        return this.lstEpisodes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemEpisode=lstEpisodes[position]
        holder.tvTitle.text="Season:"+" "+itemEpisode.season.toString()+" Episode:"+itemEpisode.episode.toString()
        holder.tvNameItem.text=itemEpisode.name
        holder.tvAirDate.text=itemEpisode.air_date


    }


}