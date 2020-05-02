package me.androidbox.data.store

import com.nhaarman.mockito_kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class ProjectsDataStoreProviderTest {


    private val projectsCacheDataStore: ProjectsCacheDataStore = ProjectsCacheDataStore(mock())
    private val projectsRemoteDataStore: ProjectsRemoteDataStore = ProjectsRemoteDataStore(mock())

    private lateinit var projectsDataStoreProvider: ProjectsDataStoreProvider

    @Before
    fun setUp() {
        projectsDataStoreProvider = ProjectsDataStoreProvider(projectsCacheDataStore, projectsRemoteDataStore)
    }

    @Test
    fun `should provide cache data store when has cache that has not expired`() {
        // Act
        val dataStore = projectsDataStoreProvider.getDataStore(isProjectCached = true, isCachedExpired = false)

        // Assert
        assertThat(dataStore).isInstanceOf(ProjectsCacheDataStore::class.java)
    }

    @Test
    fun `should provide remote data store when there is no cache`() {
        // Act
        val dataStore = projectsDataStoreProvider.getDataStore(isProjectCached = false, isCachedExpired = false)

        // Assert
        assertThat(dataStore).isInstanceOf(ProjectsRemoteDataStore::class.java)
    }

    @Test
    fun `should provide remote data store when there is cache but its expired`() {
        // Act
        val dataStore = projectsDataStoreProvider.getDataStore(isProjectCached = true, isCachedExpired = true)

        // Assert
        assertThat(dataStore).isInstanceOf(ProjectsRemoteDataStore::class.java)
    }


}
