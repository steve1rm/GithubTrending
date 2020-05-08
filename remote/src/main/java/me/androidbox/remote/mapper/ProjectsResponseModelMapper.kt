package me.androidbox.remote.mapper

import me.androidbox.data.model.ProjectEntity
import me.androidbox.remote.model.ProjectModel
import javax.inject.Inject

class ProjectsResponseModelMapper @Inject constructor() : ModelMapper<ProjectModel, ProjectEntity> {

    /* Remote -> Data */
    override fun mapFromModel(model: ProjectModel): ProjectEntity {
        return ProjectEntity(
            model.id,
            model.name,
            model.fullName,
            model.starCount,
            model.dateCreated,
            model.owner.ownerName,
            model.owner.ownerAvatar)
    }
}