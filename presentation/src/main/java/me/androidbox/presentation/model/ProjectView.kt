package me.androidbox.presentation.model

data class ProjectView(
    val projectId: Int,
    val name: String,
    val fullName: String,
    val starCount: Int,
    val dateCreated: String,
    val ownerName: String,
    val ownerAvatar: String,
    val isBookmarked: Boolean)