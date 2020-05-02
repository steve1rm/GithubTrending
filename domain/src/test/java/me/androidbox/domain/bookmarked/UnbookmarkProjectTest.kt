package me.androidbox.domain.bookmarked

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import me.androidbox.domain.executor.PostExecutionThread
import me.androidbox.domain.repository.ProjectsRepository
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class UnbookmarkProjectTest {

    private val projectsRepository: ProjectsRepository = mock()
    private val postExecutionThread: PostExecutionThread = mock()
    private lateinit var unbookProject: UnbookmarkProject

    @Before
    fun setUp() {
        unbookProject = UnbookmarkProject(projectsRepository, postExecutionThread)
    }

    @Test
    fun `should unbookmark a project`() {
        // Arrange
        val project = UnbookmarkProject.Params.forProject("projectID")
        whenever(projectsRepository.unBookmarkProject("projectID")).thenReturn(Completable.complete())

        // Act
        val testObserver = unbookProject.buildUseCaseCompletable(project).test()

        // Assert
        testObserver
            .assertComplete()
        verify(projectsRepository).unBookmarkProject("projectID")
        verifyNoMoreInteractions(projectsRepository)
    }
}
