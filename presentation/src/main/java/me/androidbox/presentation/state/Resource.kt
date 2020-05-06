package me.androidbox.presentation.state

/** TODO find a way to remove the nullable types */
data class Resource<out T> constructor(
    val status: ResourceState,
    val data: T?,
    val message: String?)
