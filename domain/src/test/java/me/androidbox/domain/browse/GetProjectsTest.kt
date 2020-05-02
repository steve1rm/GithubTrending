package me.androidbox.domain.browse

import com.nhaarman.mockito_kotlin.*
import io.reactivex.Observable
import me.androidbox.domain.executor.PostExecutionThread
import me.androidbox.domain.mockdata.ProjectDataFactory
import me.androidbox.domain.model.Project
import me.androidbox.domain.repository.ProjectsRepository
import org.junit.Before
import org.junit.Test

class GetProjectsTest {
    private val projectsRepository: ProjectsRepository = mock()
    private val postExecutionThread: PostExecutionThread = mock()
    private lateinit var getProjects: GetProjects

    @Before
    fun setUp() {
        getProjects = GetProjects(projectsRepository, postExecutionThread)
    }

    @Test
    fun `should call getProjects`() {
        // Arrange
        whenever(projectsRepository.getProjects())
            .thenReturn(Observable.just(ProjectDataFactory.makeProjectList(2)))

        // Act
        val testObserver = getProjects.buildUseCaseObservable().test()

        // Assert
        testObserver
            .assertValueCount(1)
            .assertComplete()

        verify(projectsRepository).getProjects()
        verifyNoMoreInteractions(projectsRepository)
    }

    @Test
    fun `should call getProjects and return project data`() {
        // Arrange
        val projectList = ProjectDataFactory.makeProjectList(2)

        whenever(projectsRepository.getProjects())
            .thenReturn(Observable.just(projectList))

        // Act
        val testObserver = getProjects.buildUseCaseObservable().test()

        // Assert
        testObserver
            .assertValueCount(1)
            .assertValue(projectList)
            .assertComplete()

        verify(projectsRepository).getProjects()
        verifyNoMoreInteractions(projectsRepository)
    }


    private fun stubGetProjects(observable: Observable<List<Project>>) {
        whenever(projectsRepository.getProjects()).thenReturn(observable)
    }
}