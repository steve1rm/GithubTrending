package me.androidbox.presentation.mockdata

import java.util.*

object DataFactory {

    fun randomString(): String {
        return UUID.randomUUID().toString()
    }

    fun randomInt(): Int {
        return (0..1000).shuffled().first()
    }

    fun randomLong(): Long {
        return randomInt().toLong()
    }

    fun randomBoolean(): Boolean {
        return (0..1).shuffled().first() == 1
    }
}