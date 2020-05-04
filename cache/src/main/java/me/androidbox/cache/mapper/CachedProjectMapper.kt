package me.androidbox.cache.mapper

import me.androidbox.cache.model.CacheProject
import me.androidbox.data.model.ProjectEntity

class CachedProjectMapper : CacheMapper<CacheProject, ProjectEntity> {

    override fun mapFromCached(cacheProject: CacheProject): ProjectEntity {
        return ProjectEntity(
            cacheProject.projectId,
            cacheProject.name,
            cacheProject.fullName,
            cacheProject.starCount,
            cacheProject.dateCreated,
            cacheProject.ownerName,
            cacheProject.ownerName)
    }

    override fun mapToCached(projectEntity: ProjectEntity): CacheProject {
        return CacheProject(
            projectEntity.id,
            projectEntity.name,
            projectEntity.fullName,
            projectEntity.starCount,
            projectEntity.dateCreated,
            projectEntity.ownerName,
            projectEntity.ownerAvatar,
            projectEntity.isBookmarked)
    }
}
