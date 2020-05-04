package me.androidbox.cache.mapper

import me.androidbox.cache.mockdata.ProjectDataFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class CachedProjectMapperTest {

    private lateinit var cachedProjectMapper: CachedProjectMapper

    @Before
    fun setUp() {
        cachedProjectMapper = CachedProjectMapper()
    }

    @Test
    fun `should map from cached`() {
        // Arrange
        val cacheProject = ProjectDataFactory.makeCachedProject()

        // Act
        val projectEntity = cachedProjectMapper.mapFromCached(cacheProject)

        // Assert
        assertThat(projectEntity).isEqualToComparingFieldByField(cacheProject)
    }

    @Test
    fun `should map to cached`() {
        // Arrange
        val projectEntity = ProjectDataFactory.makeProjectEntity()

        // Act
        val cacheProject = cachedProjectMapper.mapToCached(projectEntity)

        // Assert
        assertThat(cacheProject).isEqualToComparingFieldByField(projectEntity)
    }
}