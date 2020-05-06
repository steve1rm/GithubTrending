package me.androidbox.presentation.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.*
import io.reactivex.observers.DisposableObserver
import me.androidbox.domain.bookmarked.BookmarkProject
import me.androidbox.domain.bookmarked.UnbookmarkProject
import me.androidbox.domain.browse.GetProjects
import me.androidbox.domain.model.Project
import me.androidbox.presentation.mapper.Mapper
import me.androidbox.presentation.mockdata.DataFactory
import me.androidbox.presentation.mockdata.ProjectFactory
import me.androidbox.presentation.model.ProjectView
import me.androidbox.presentation.state.ResourceState
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Captor
import java.lang.IllegalArgumentException
import java.lang.RuntimeException

class BrowseProjectsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getProjects: GetProjects = mock()
    private val bookmarkProject: BookmarkProject = mock()
    private val unBookmarkProject: UnbookmarkProject = mock()
    private val projectViewMapperImp: Mapper<ProjectView, Project> = mock()

    private lateinit var browseProjectsViewModel: BrowseProjectsViewModel

    @Captor
    private val captor = argumentCaptor<DisposableObserver<List<Project>>>()

    @Before
    fun setUp() {
        browseProjectsViewModel = BrowseProjectsViewModel(
            getProjects,
            bookmarkProject,
            unBookmarkProject,
            projectViewMapperImp)
    }

    @Test
    fun `fetch project executes use case`() {
        // Act
        browseProjectsViewModel.fetchProjects()

        // Assert
        verify(getProjects).execute(any(), eq(null))
    }

    @Test
    fun `fetch project returns success`() {
        // Arrange
        val projects = ProjectFactory.makeProjectList(2)
        val projectView = ProjectFactory.makeProjectViewList(2)

        stubProjectMapperMapToView(projectView[0], projects[0])
        stubProjectMapperMapToView(projectView[1], projects[1])

        // Act
        browseProjectsViewModel.fetchProjects()

        // Assert
        verify(getProjects).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(projects)
        assertThat(ResourceState.SUCCESS).isEqualTo(browseProjectsViewModel.getProjects().value?.status)
    }


    @Test
    fun `fetch project returns data`() {
        // Arrange
        val projects = ProjectFactory.makeProjectList(2)
        val projectView = ProjectFactory.makeProjectViewList(2)

        stubProjectMapperMapToView(projectView[0], projects[0])
        stubProjectMapperMapToView(projectView[1], projects[1])

        // Act
        browseProjectsViewModel.fetchProjects()

        // Assert
        verify(getProjects).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(projects)
        assertThat(projectView).isEqualTo(browseProjectsViewModel.getProjects().value?.data)
    }

    @Test
    fun `fetch project returns error`() {
        // Act
        browseProjectsViewModel.fetchProjects()

        // Assert
        verify(getProjects).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())
        assertThat(ResourceState.ERROR).isEqualTo(browseProjectsViewModel.getProjects().value?.status)
    }

    @Test
    fun `fetch project returns message error`() {
        // Arrange
        val errorMessage = DataFactory.randomString()

        // Act
        browseProjectsViewModel.fetchProjects()

        // Assert
        verify(getProjects).execute(captor.capture(), eq(null))
        captor.firstValue.onError(IllegalArgumentException(errorMessage))
        assertThat(errorMessage).isEqualTo(browseProjectsViewModel.getProjects().value?.message)
    }

    private fun stubProjectMapperMapToView(projectView: ProjectView, project: Project) {
        whenever(projectViewMapperImp.mapToView(project)).thenReturn(projectView)
    }
}
