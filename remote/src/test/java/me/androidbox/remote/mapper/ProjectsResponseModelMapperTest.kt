package me.androidbox.remote.mapper

import me.androidbox.remote.mockdata.ProjectDataFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

class ProjectsResponseModelMapperTest {

    private lateinit var projectsResponseModelMapper: ProjectsResponseModelMapper

    @Before
    fun setUp() {
        projectsResponseModelMapper = ProjectsResponseModelMapper()
    }

    @Test
    fun `should map from remote to data`() {
        // Arrange
        val projectModel = ProjectDataFactory.makeProject()

        // Act
        val projectEntity = projectsResponseModelMapper.mapFromModel(projectModel)

        // Assert
        assertThat(projectEntity).isEqualToComparingFieldByField(projectModel)
    }
}
