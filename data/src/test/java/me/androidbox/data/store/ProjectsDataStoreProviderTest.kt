package me.androidbox.data.store

import com.nhaarman.mockito_kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class ProjectsDataStoreProviderTest {

    private val projectsCacheDataStore: ProjectsCacheDataStore = mock()
    private val projectsRemoteDataStore: ProjectsRemoteDataStore = mock()
    private lateinit var projectsDataStoreProvider: ProjectsDataStoreProvider

    @Before
    fun setUp() {
        projectsDataStoreProvider = ProjectsDataStoreProvider(projectsCacheDataStore, projectsRemoteDataStore)
    }

    @Test
    fun `should provide cache data store when has cache that has not expired`() {
        // Act
        val dataStore = projectsDataStoreProvider.getDataStore(isProjectCached = false, isCachedExpired = false)

        // Assert
        assertThat(dataStore).isInstanceOf(ProjectsCacheDataStore::class.java)
    }
}
