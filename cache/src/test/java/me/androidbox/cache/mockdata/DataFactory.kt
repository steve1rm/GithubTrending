package me.androidbox.cache.mockdata

import java.util.*

object DataFactory {

    fun randomUuid(): String = UUID.randomUUID().toString()

    fun randomInt(): Int = (0..1000).shuffled().first()

    fun randomLong(): Long = (0..1000).shuffled().first().toLong()

    fun randomBoolean(): Boolean = (0..1).shuffled().first() == 1

    fun makeStringList(count: Int): List<String> {
        val items = mutableListOf<String>()

        repeat(count) {
            items.add(randomUuid())
        }

        return items
    }
}
