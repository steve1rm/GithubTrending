package me.androidbox.remote.mockdata

import java.util.*
import java.util.concurrent.ThreadLocalRandom

object DataFactory {

    fun randomUuid(): String = UUID.randomUUID().toString()

    fun randomInt(): Int = (0..1000).shuffled().first()

    fun randomLong(): Long = randomInt().toLong()

    fun randomBoolean(): Boolean = (0..1).shuffled().first() == 1
}
