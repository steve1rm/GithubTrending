package me.androidbox.presentation.mapper

interface Mapper<V, D> {

    fun mapToView(model: D): V
}