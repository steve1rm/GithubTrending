package me.androidbox.presentation.mapper

import me.androidbox.presentation.mockdata.ProjectFactory
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class ProjectViewMapperImpTest {

    private val mapper = ProjectViewMapperImp()

    @Test
    fun mapToViewMapsData() {
        val project = ProjectFactory.makeProject()
        val projectView = mapper.mapToView(project)

        assertEquals(project.projectId, projectView.projectId)
        assertEquals(project.name, projectView.name)
        assertEquals(project.fullName, projectView.fullName)
        assertEquals(project.starCount, projectView.starCount)
        assertEquals(project.dateCreated, projectView.dateCreated)
        assertEquals(project.ownerName, projectView.ownerName)
        assertEquals(project.ownerAvatar, projectView.ownerAvatar)
        assertEquals(project.isBookmarked, projectView.isBookmarked)
    }
}
