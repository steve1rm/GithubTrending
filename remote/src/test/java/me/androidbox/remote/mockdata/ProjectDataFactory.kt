package me.androidbox.remote.mockdata

import me.androidbox.data.model.ProjectEntity
import me.androidbox.remote.model.OwnerModel
import me.androidbox.remote.model.ProjectModel
import me.androidbox.remote.model.ProjectsResponseModel

object ProjectDataFactory {

    fun makeOwner(): OwnerModel =
        OwnerModel(DataFactory.randomUuid(), DataFactory.randomUuid())

    fun makeProject(): ProjectModel =
        ProjectModel(
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            DataFactory.randomInt(),
            DataFactory.randomUuid(),
            makeOwner())

    fun makeProjectEntity(): ProjectEntity =
        ProjectEntity(
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            DataFactory.randomInt(),
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            DataFactory.randomUuid(),
            DataFactory.randomBoolean())


    fun makeProjectsResponse(): ProjectsResponseModel =
        ProjectsResponseModel(listOf(makeProject(), makeProject()))
}
