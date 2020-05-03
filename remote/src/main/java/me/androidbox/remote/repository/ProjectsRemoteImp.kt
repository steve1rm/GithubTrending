package me.androidbox.remote.repository

import io.reactivex.Observable
import me.androidbox.data.model.ProjectEntity
import me.androidbox.data.repository.ProjectsRemote
import me.androidbox.remote.mapper.ModelMapper
import me.androidbox.remote.model.ProjectModel
import me.androidbox.remote.service.GithubTrendingService
import javax.inject.Inject

class ProjectsRemoteImp @Inject constructor(
    private val githubTrendingService: GithubTrendingService,
    private val projectsResponseModelMapper: ModelMapper<ProjectModel, ProjectEntity>) : ProjectsRemote {

    override fun getProjects(): Observable<List<ProjectEntity>> {
        return githubTrendingService.searchRepositories("language:kotlin", "starts", "descending")
            .map { projectsResponseModel ->
                projectsResponseModel.items.map {  projectModel ->
                    projectsResponseModelMapper.mapFromModel(projectModel)
                }
            }
    }
}
