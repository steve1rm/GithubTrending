package me.androidbox.data.model

data class ProjectEntity(
    val projectId: Int,
    val name: String,
    val fullName: String,
    val starCount: Int,
    val dateCreated: String,
    val ownerName: String,
    val ownerAvatar: String,
    val isBookMarked: Boolean = false)
