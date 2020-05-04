package me.androidbox.remote.repository

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import me.androidbox.data.model.ProjectEntity
import me.androidbox.remote.mapper.ModelMapper
import me.androidbox.remote.mockdata.ProjectDataFactory
import me.androidbox.remote.model.ProjectModel
import me.androidbox.remote.service.GithubTrendingService
import org.junit.Before
import org.junit.Test

class ProjectsRemoteImpTest {

    private val githubTrendingService: GithubTrendingService = mock()
    private val projectsResponseModelMapper: ModelMapper<ProjectModel, ProjectEntity> = mock()
    private lateinit var projectsRemoteImp: ProjectsRemoteImp

    @Before
    fun setUp() {
        projectsRemoteImp = ProjectsRemoteImp(githubTrendingService, projectsResponseModelMapper)
    }

    @Test
    fun `should get projects`() {
        // Arrange
        val projectsResponseModel = ProjectDataFactory.makeProjectsResponse()
        val projectEntity = ProjectDataFactory.makeProjectEntity()
        val projectModel = ProjectDataFactory.makeProject()

        whenever(githubTrendingService.searchRepositories("language:kotlin", "starts", "descending"))
            .thenReturn(Observable.just(projectsResponseModel))
        whenever(projectsResponseModelMapper.mapFromModel(projectModel)).thenReturn(projectEntity)

        // Act
        val testObserver = projectsRemoteImp.getProjects().test()

        // Assert
        testObserver
            .assertComplete()
            .assertValueCount(1)

        verify(githubTrendingService).searchRepositories("language:kotlin", "starts", "descending")
        verifyNoMoreInteractions(githubTrendingService)
      //  verify(projectsResponseModelMapper).mapFromModel(projectModel)
//        verifyNoMoreInteractions(projectsResponseModelMapper)
    }
}
