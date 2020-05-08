package me.androidbox.mobile.di.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import me.androidbox.data.model.ProjectEntity
import me.androidbox.data.repository.ProjectsRemote
import me.androidbox.mobile.BuildConfig.DEBUG
import me.androidbox.remote.mapper.ModelMapper
import me.androidbox.remote.mapper.ProjectsResponseModelMapper
import me.androidbox.remote.model.ProjectModel
import me.androidbox.remote.repository.ProjectsRemoteImp
import me.androidbox.remote.service.GithubTrendingService
import me.androidbox.remote.service.GithubTrendingServiceFactory

@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideGithubService(): GithubTrendingService {
            return GithubTrendingServiceFactory.makeGithubTrendingService(DEBUG)
        }
    }

    @Binds
    abstract fun bindProjectsRemote(projectsRemoteImp: ProjectsRemoteImp): ProjectsRemote

    @Binds
    abstract fun bindProjectsResponseModelMapper(projectsResponseModelMapper: ProjectsResponseModelMapper): ModelMapper<ProjectModel, ProjectEntity>
}