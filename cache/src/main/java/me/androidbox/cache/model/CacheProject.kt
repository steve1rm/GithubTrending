package me.androidbox.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import me.androidbox.cache.db.ProjectConstants

@Entity(tableName = ProjectConstants.TABLE_NAME_PROJECTS)
data class CacheProject(
    @PrimaryKey(autoGenerate = true)
    var projectId: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "fullname")
    var fullName: String,

    @ColumnInfo(name = "starCount")
    var starCount: Int,

    @ColumnInfo(name = "dateCreated")
    var dateCreated: String,

    @ColumnInfo(name = "ownerName")
    var ownerName: String,

    @ColumnInfo(name = "ownerAvatar")
    var ownerAvatar: String,

    @ColumnInfo(name = "isBookMarked")
    var isBookMarked: Boolean
)


