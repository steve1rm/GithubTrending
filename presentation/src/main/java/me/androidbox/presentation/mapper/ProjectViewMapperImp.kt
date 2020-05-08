package me.androidbox.presentation.mapper

import me.androidbox.domain.model.Project
import me.androidbox.presentation.model.ProjectView
import javax.inject.Inject

open class ProjectViewMapperImp @Inject constructor() : Mapper<ProjectView, Project> {

    override fun mapToView(model: Project): ProjectView {
        return ProjectView(
            model.projectId,
            model.name,
            model.fullName,
            model.starCount,
            model.dateCreated,
            model.ownerName,
            model.ownerAvatar,
            model.isBookmarked)
    }
}
