package me.androidbox.mobile.mapper

import me.androidbox.mobile.model.Project
import me.androidbox.presentation.model.ProjectView
import javax.inject.Inject

class ProjectViewMapperImp @Inject constructor()
    : ViewMapper<ProjectView, Project> {

    override fun mapFromView(presentation: ProjectView): Project {
        return Project(
            presentation.projectId,
            presentation.name,
            presentation.fullName,
            presentation.starCount,
            presentation.dateCreated,
            presentation.ownerName,
            presentation.ownerAvatar,
            presentation.isBookmarked)
    }
}
