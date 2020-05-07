package me.androidbox.mobile.model

data class Project(
    val projectId: Int,
    val name: String,
    val fullName: String,
    val starCount: Int,
    val ownerAvatar: String,
    val isBookmarked: Boolean)

