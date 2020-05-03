package me.androidbox.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import me.androidbox.cache.db.ConfigConstants

@Entity(tableName = ConfigConstants.TABLE_NAME_CONFIG)
data class Config(
    @PrimaryKey(autoGenerate = true)
    var id: Int = -1,

    @ColumnInfo(name = "lastCacheTime")
    var lastCacheTime: Long)
