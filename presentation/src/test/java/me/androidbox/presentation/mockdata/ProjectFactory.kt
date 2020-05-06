package me.androidbox.presentation.mockdata

import me.androidbox.domain.model.Project
import me.androidbox.presentation.model.ProjectView

object ProjectFactory {
    fun makeProjectView(): ProjectView {
        return ProjectView(
            DataFactory.randomInt(),
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomInt(),
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomBoolean())
    }

    fun makeProject(): Project {
        return Project(DataFactory.randomInt(),
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomInt(),
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomString(),
            DataFactory.randomBoolean())
    }

    fun makeProjectViewList(count: Int): List<ProjectView> {
        val projects = mutableListOf<ProjectView>()
        repeat(count) {
            projects.add(makeProjectView())
        }
        return projects
    }

    fun makeProjectList(count: Int): List<Project> {
        val projects = mutableListOf<Project>()
        repeat(count) {
            projects.add(makeProject())
        }
        return projects
    }
}