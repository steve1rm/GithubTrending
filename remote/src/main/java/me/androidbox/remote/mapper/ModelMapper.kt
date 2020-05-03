package me.androidbox.remote.mapper

interface ModelMapper<in M, out E> {

    /* Remote -> Data */
    fun mapFromModel(model: M): E
}
