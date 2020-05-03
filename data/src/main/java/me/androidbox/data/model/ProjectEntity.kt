package me.androidbox.data.model

class ProjectEntity(val id: String,
                    val name: String,
                    val fullName: String,
                    val starCount: Int,
                    val dateCreated: String,
                    val ownerName: String,
                    val ownerAvatar: String,
                    val isBookmarked: Boolean = false)
