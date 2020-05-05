package me.androidbox.cache.mockdata

import me.androidbox.cache.model.CacheProject
import me.androidbox.data.model.ProjectEntity

object ProjectDataFactory {

    fun makeCachedProject(): CacheProject {
        return CacheProject(
            DataFactory.randomInt(),
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            DataFactory.randomInt(),
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            DataFactory.randomBoolean())
    }

    fun makeNotBookMarkedCachedProject(): CacheProject {
        return CacheProject(
            DataFactory.randomInt(),
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            DataFactory.randomInt(),
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            false)
    }

    fun makeBookMarkedCachedProject(): CacheProject {
        return CacheProject(
            DataFactory.randomInt(),
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            DataFactory.randomInt(),
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            true)
    }


    fun makeBookmarkedCachedProject(): CacheProject {
        return CacheProject(
            DataFactory.randomInt(),
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            DataFactory.randomInt(),
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            true)
    }

    fun makeProjectEntity(): ProjectEntity {
        return ProjectEntity(
            DataFactory.randomInt(),
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            DataFactory.randomInt(),
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            DataFactory.randomBoolean())
    }

    fun makeBookmarkedProjectEntity(): ProjectEntity {
        return ProjectEntity(
            DataFactory.randomInt(),
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            DataFactory.randomInt(),
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            true)
    }

    fun makeNotBookmarkedProjectEntity(): ProjectEntity {
        return ProjectEntity(
            DataFactory.randomInt(),
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            DataFactory.randomInt(),
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            false)
    }
}
