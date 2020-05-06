package me.androidbox.presentation.mapper

interface Mapper<out V, in D> {

    fun mapToView(model: D): V
}