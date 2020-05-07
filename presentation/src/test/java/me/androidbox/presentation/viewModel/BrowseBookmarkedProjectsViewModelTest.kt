package me.androidbox.presentation.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.*
import io.reactivex.observers.DisposableObserver
import me.androidbox.domain.bookmarked.GetBookedMarkedProjects
import me.androidbox.domain.model.Project
import me.androidbox.presentation.mapper.Mapper
import me.androidbox.presentation.mockdata.ProjectFactory
import me.androidbox.presentation.model.ProjectView
import me.androidbox.presentation.state.ResourceState
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BrowseBookmarkedProjectsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getBookedMarkedProjects: GetBookedMarkedProjects = mock()
    private val projectViewMapper: Mapper<ProjectView, Project> = mock()
    private lateinit var browseBookedMarkedProjects: BrowseBookmarkedProjectsViewModel

    private val argumentCaptor
            = argumentCaptor<DisposableObserver<List<Project>>>()

    @Before
    fun setUp() {
        browseBookedMarkedProjects = BrowseBookmarkedProjectsViewModel(
            getBookedMarkedProjects,
            projectViewMapper)
    }

    @Test
    fun `should call execute when fetching projects`() {
        // Act
        browseBookedMarkedProjects.fetchProjects()

        // Assert
        verify(getBookedMarkedProjects).execute(any(), eq(null))
    }

    @Test
    fun `should fetch projects with loading and success`() {
        // Arrange
        val projectView = ProjectFactory.makeProjectView()
        val project = ProjectFactory.makeProject()

        whenever(projectViewMapper.mapToView(project)).thenReturn(projectView)

        // Act
        browseBookedMarkedProjects.fetchProjects()

        // Assert
        verify(getBookedMarkedProjects).execute(any(), eq(null))
        verify(getBookedMarkedProjects).execute(argumentCaptor.capture(), eq(null))
        assertThat(browseBookedMarkedProjects.getProjects().value?.status).isEqualTo(ResourceState.LOADING)
        argumentCaptor.firstValue.onNext(listOf(project))
        assertThat(browseBookedMarkedProjects.getProjects().value?.status).isEqualTo(ResourceState.SUCCESS)
        assertThat(browseBookedMarkedProjects.getProjects().value?.data).isEqualTo(listOf(projectView))
    }

    @Test
    fun `should fetch projects with loading and error`() {
        // Arrange
        val projectView = ProjectFactory.makeProjectView()
        val project = ProjectFactory.makeProject()

        whenever(projectViewMapper.mapToView(project)).thenReturn(projectView)

        // Act
        browseBookedMarkedProjects.fetchProjects()

        // Assert
        verify(getBookedMarkedProjects).execute(any(), eq(null))
        verify(getBookedMarkedProjects).execute(argumentCaptor.capture(), eq(null))
        assertThat(browseBookedMarkedProjects.getProjects().value?.status).isEqualTo(ResourceState.LOADING)

        argumentCaptor.firstValue.onError(IllegalStateException("Something bad happened"))
        assertThat(browseBookedMarkedProjects.getProjects().value?.status).isEqualTo(ResourceState.ERROR)
        assertThat(browseBookedMarkedProjects.getProjects().value?.message).isEqualTo("Something bad happened")
    }
}