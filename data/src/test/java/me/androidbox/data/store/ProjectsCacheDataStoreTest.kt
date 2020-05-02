package me.androidbox.data.store

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import me.androidbox.data.mockdata.ProjectFactory
import me.androidbox.data.repository.ProjectsCache
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class ProjectsCacheDataStoreTest {

    private val projectsCache: ProjectsCache = mock()
    private lateinit var projectsCacheDataStore: ProjectsCacheDataStore

    @Before
    fun setUp() {
        projectsCacheDataStore = ProjectsCacheDataStore(projectsCache)
    }

    @Test
    fun `should get projects`() {
        // Arrange
        val projectEntity = ProjectFactory.makeProjectEntity()
        whenever(projectsCache.getProjects()).thenReturn(Observable.just(listOf(projectEntity)))

        // Act
        val testObserver = projectsCacheDataStore.getProjects().test()

        // Assert
        testObserver
            .assertComplete()
            .assertValueCount(1)
            .assertValue(listOf(projectEntity))
        verify(projectsCache).getProjects()
    }

    @Test
    fun `should save projects`() {
        // Arrange
        val projectEntity = ProjectFactory.makeProjectEntity()
        whenever(projectsCache.saveProjects(listOf(projectEntity))).thenReturn(Completable.complete())
        whenever(projectsCache.setLastCacheTime(any())).thenReturn(Completable.complete())

        // Act
        val testObserver = projectsCacheDataStore.saveProjects(listOf(projectEntity)).test()

        // Assert
        testObserver
            .assertComplete()
        verify(projectsCache).saveProjects(listOf(projectEntity))
        verify(projectsCache).setLastCacheTime(any())
    }

    @Test
    fun `should clear projects`() {
        // Arrange
        whenever(projectsCache.clearProjects()).thenReturn(Completable.complete())
        whenever(projectsCache.setLastCacheTime(any())).thenReturn(Completable.complete())

        // Act
        val testObserver = projectsCacheDataStore.clearProjects().test()

        // Assert
        testObserver
            .assertComplete()
        verify(projectsCache).clearProjects()
    }

    @Test
    fun `should get booked marked projects`() {
        // Arrange
        val projectEntity = ProjectFactory.makeProjectEntity()
        whenever(projectsCache.getBookmarkedProjects()).thenReturn(Observable.just(listOf(projectEntity)))

        // Act
        val testObserver = projectsCacheDataStore.getBookedmarkedProjects().test()

        // Assert
        testObserver
            .assertComplete()
        verify(projectsCache).getBookmarkedProjects()
    }


    @Test
    fun `should set project as booked marked`() {
        // Arrange
        whenever(projectsCache.setProjectAsBookmarked("projectId")).thenReturn(Completable.complete())

        // Act
        val testObserver = projectsCacheDataStore.setProjectAsBookedmarked("projectId").test()

        // Assert
        testObserver
            .assertComplete()
        verify(projectsCache).setProjectAsBookmarked("projectId")
    }

    @Test
    fun `should set project as not booked marked`() {
        // Arrange
        whenever(projectsCache.setProjectAsNotBookmarked("projectId")).thenReturn(Completable.complete())

        // Act
        val testObserver = projectsCacheDataStore.setProjectAsNotBookedmarked("projectId").test()

        // Assert
        testObserver
            .assertComplete()
        verify(projectsCache).setProjectAsNotBookmarked("projectId")
    }

}