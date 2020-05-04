package me.androidbox.cache.mockdata

import me.androidbox.cache.model.Config

object ConfigDataFactory {
    fun makeCachedConfig(): Config {
        return Config(
            DataFactory.randomInt(),
            DataFactory.randomLong())
    }
}
