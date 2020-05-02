package me.androidbox.domain.mockdata

import me.androidbox.domain.model.Project
import java.util.*

object ProjectDataFactory {

    private fun randomUuid(): String {
        return UUID.randomUUID().toString()
    }

    private fun randomBoolean(): Boolean {
        return (0..1).shuffled().first() == 1
    }

    private fun makeProject(): Project {
        return Project(
            randomUuid(),
            randomUuid(),
            randomUuid(),
            randomUuid(),
            randomUuid(),
            randomUuid(),
            randomUuid(),
            randomBoolean())
    }

    fun makeProjectList(count: Int): List<Project> {
        val projects = mutableListOf<Project>()

        repeat(count) {
            projects.add(makeProject())
        }

        return projects
    }

}