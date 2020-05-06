package me.androidbox.data.repository

import com.nhaarman.mockito_kotlin.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import me.androidbox.data.mapper.ProjectMapper
import me.androidbox.data.mockdata.ProjectFactory
import me.androidbox.data.model.ProjectEntity
import me.androidbox.data.store.ProjectsCacheDataStore
import me.androidbox.data.store.ProjectsDataStoreProvider
import me.androidbox.data.store.ProjectsRemoteDataStore
import me.androidbox.domain.model.Project
import org.junit.Before
import org.junit.Test

class ProjectsDataRepositoryTest {

    private val projectMapper = ProjectMapper()
    private val projectsCacheDataStore: ProjectsCacheDataStore = mock()
    private val projectsRemoteDataStore: ProjectsRemoteDataStore = mock()
    private val projectsDataStoreProvider: ProjectsDataStoreProvider = mock()
    private val projectsCache: ProjectsCache = mock()
    private lateinit var projectsDataRepository: ProjectsDataRepository

    @Before
    fun setUp() {
        projectsDataRepository = ProjectsDataRepository(
            projectMapper,
            projectsDataStoreProvider,
            projectsCache)
    }

    @Test
    fun `should return a list of project mapped to the domain`() {
        // Arrange
        val projectEntity = ProjectFactory.makeProjectEntity()

        whenever(projectsCache.isProjectCached()).thenReturn(Single.just(true))
        whenever(projectsCache.isProjectsCacheExpired()).thenReturn(Single.just(false))
        whenever(projectsDataStoreProvider.getDataStore(any(), any())).thenReturn(projectsCacheDataStore)
        whenever(projectsCacheDataStore.getProjects()).thenReturn(Observable.just(listOf(projectEntity)))
        whenever(projectsDataStoreProvider.getCachedDataStore()).thenReturn(projectsCacheDataStore)
        whenever(projectsCacheDataStore.saveProjects(listOf(projectEntity))).thenReturn(Completable.complete())

        val testObserver = projectsDataRepository.getProjects().test()

        testObserver
            .assertComplete()
            .assertValueCount(1)
            .assertValue { projectList ->
                var isMatched = false
                projectList.map {
                    isMatched = checkMatchesEntity(it, projectEntity)
                }

                isMatched
            }

        verify(projectsCache).isProjectCached()
        verify(projectsCache).isProjectsCacheExpired()
    }

    @Test
    fun `should set project as booked marked`() {
        // Arrange
        whenever(projectsDataStoreProvider.getCachedDataStore()).thenReturn(projectsCacheDataStore)
        whenever(projectsDataStoreProvider.getCachedDataStore().setProjectAsBookedmarked("projectId"))
            .thenReturn(Completable.complete())

        // Act
        val testObserver = projectsDataRepository.bookmarkProject("projectId").test()

        // Assert
        testObserver
            .assertComplete()

        verify(projectsDataStoreProvider.getCachedDataStore()).setProjectAsBookedmarked("projectId")
    }

    private fun checkMatchesEntity(project: Project, projectEntity: ProjectEntity): Boolean {
        return project.projectId.contentEquals(projectEntity.id) &&
                project.name.contentEquals(projectEntity.name) &&
                project.isBookmarked == projectEntity.isBookmarked &&
                project.fullName.contentEquals(projectEntity.fullName) &&
                project.starCount.contentEquals(projectEntity.starCount) &&
                project.dateCreated.contentEquals(projectEntity.dateCreated) &&
                project.ownerName.contentEquals(projectEntity.ownerName) &&
                project.ownerAvatar.contentEquals(projectEntity.ownerAvatar)
    }
}
