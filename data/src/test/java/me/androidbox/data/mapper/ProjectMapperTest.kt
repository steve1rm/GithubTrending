package me.androidbox.data.mapper

import me.androidbox.data.mockdata.ProjectFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class ProjectMapperTest {

    private lateinit var projectMapper: ProjectMapper

    @Before
    fun setUp() {
        projectMapper = ProjectMapper()
    }

    @Test
    fun `should map from entity`() {
        // Arrange
        val entity = ProjectFactory.makeProjectEntity()

        // Act
        val model = projectMapper.mapFromEntity(entity)

        // Assert
        assertThat(model).isEqualToComparingFieldByField(entity)
    }

    @Test
    fun `should map to entity`() {
        // Arrange
        val model = ProjectFactory.makeProject()

        // Act
        val entity = projectMapper.mapToEntity(model)

        // Assert
        assertThat(entity).isEqualToComparingFieldByField(model)
    }
}
