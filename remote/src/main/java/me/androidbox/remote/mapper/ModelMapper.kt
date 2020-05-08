package me.androidbox.remote.mapper

/* TODO ModelMapper<in M, out E> fails to compile in RemoteModule
*   https://github.com/google/dagger/issues/1143 maybe surpress wildcards */

interface ModelMapper<M, E> {

    /* Remote -> Data */
    fun mapFromModel(model: M): E
}
