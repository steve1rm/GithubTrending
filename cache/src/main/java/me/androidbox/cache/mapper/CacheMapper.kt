package me.androidbox.cache.mapper

interface CacheMapper<C, E> {

    fun mapFromCached(cacheProject: C):  E

    fun mapToCached(projectEntity: E) : C
}
