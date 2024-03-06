package com.example.apitvshowmvvm.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_shows_table")
data class TVShowEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id:Int,
    @ColumnInfo(name = "name")
    val name:String,

    @ColumnInfo(name = "start_date")
    val starDate:String,

    @ColumnInfo(name = "start_date")
    val country:String,
    @ColumnInfo(name = "network")
    val network:String,
    @ColumnInfo(name = "status")
    val status:String,
    @ColumnInfo(name = "thumbnail")
    val thumbnail:String
)
