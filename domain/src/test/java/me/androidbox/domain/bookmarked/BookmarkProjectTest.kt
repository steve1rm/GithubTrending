package me.androidbox.domain.bookmarked

import com.nhaarman.mockito_kotlin.*
import io.reactivex.Completable
import me.androidbox.domain.executor.PostExecutionThread
import me.androidbox.domain.repository.ProjectsRepository
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class BookmarkProjectTest {

    private val projectsRepository: ProjectsRepository = mock()
    private val postExecutionThread: PostExecutionThread = mock()
    private lateinit var bookmarkProject: BookmarkProject

    @Before
    fun setUp() {
        bookmarkProject = BookmarkProject(projectsRepository, postExecutionThread)
    }

    @Test
    fun `book mark the project with project ID`() {
        // Arrange
        whenever(projectsRepository.bookmarkProject("projectID"))
            .thenReturn(Completable.complete())
        val project = BookmarkProject.Params.forProject("projectID")

        // Act
        val testObserver = bookmarkProject.buildUseCaseCompletable(project).test()

        // Assert
        testObserver
            .assertComplete()
        verify(projectsRepository).bookmarkProject("projectID")
        verifyNoMoreInteractions(projectsRepository)
    }
}