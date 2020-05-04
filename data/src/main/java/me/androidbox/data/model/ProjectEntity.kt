package me.androidbox.data.model

data class ProjectEntity(
    val id: Int,
    val name: String,
    val fullName: String,
    val starCount: Int,
    val dateCreated: String,
    val ownerName: String,
    val ownerAvatar: String,
    val isBookmarked: Boolean = false)
