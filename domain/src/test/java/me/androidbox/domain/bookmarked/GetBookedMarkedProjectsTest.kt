package me.androidbox.domain.bookmarked

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import me.androidbox.domain.browse.GetProjects
import me.androidbox.domain.executor.PostExecutionThread
import me.androidbox.domain.mockdata.ProjectDataFactory
import me.androidbox.domain.repository.ProjectsRepository
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class GetBookedMarkedProjectsTest {

    private val projectsRepository: ProjectsRepository = mock()
    private val postExecutionThread: PostExecutionThread = mock()
    private lateinit var getBookedMarkedProjects: GetBookedMarkedProjects

    @Before
    fun setup() {
        getBookedMarkedProjects = GetBookedMarkedProjects(projectsRepository, postExecutionThread)
    }

    @Test
    fun `should get booked marked projects`() {
        // Arrange
        val projectList = ProjectDataFactory.makeProjectList(2)
        whenever(projectsRepository.getBookmarkedProjects())
            .thenReturn(Observable.just(projectList))

        // Act
        val testObserver = getBookedMarkedProjects.buildUseCaseObservable().test()

        // Assert
        testObserver
            .assertValueCount(1)
            .assertValue(projectList)
            .assertComplete()
        verify(projectsRepository).getBookmarkedProjects()
        verifyNoMoreInteractions(projectsRepository)
    }
}
