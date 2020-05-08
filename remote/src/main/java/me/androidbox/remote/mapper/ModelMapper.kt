package me.androidbox.remote.mapper

/* TODO ModelMapper<in M, out E> fails to compile in RemoteModule */

interface ModelMapper<M, E> {

    /* Remote -> Data */
    fun mapFromModel(model: M): E
}
