package me.androidbox.data.mapper

import me.androidbox.data.model.ProjectEntity
import me.androidbox.domain.model.Project
import javax.inject.Inject

class ProjectMapper @Inject constructor() : EntityMapper<ProjectEntity, Project> {

    /* Data -> Domain */
    override fun mapFromEntity(entity: ProjectEntity): Project {
        return Project(
            entity.projectId,
            entity.name,
            entity.fullName, 
            entity.starCount,
            entity.dateCreated,
            entity.ownerName,
            entity.ownerAvatar,
            entity.isBookMarked)
    }

    /* Domain -> Data */
    override fun mapToEntity(domain: Project): ProjectEntity {
        return ProjectEntity(
            domain.id,
            domain.name,
            domain.fullName,
            domain.starCount,
            domain.dateCreated,
            domain.ownerName,
            domain.ownerAvatar,
            domain.isBookmarked)
    }
}
