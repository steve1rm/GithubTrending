package me.androidbox.mobile.di.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import me.androidbox.mobile.BuildConfig.DEBUG
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
    abstract fun bindProjectsRemote(projectsRemoteImp: ProjectsRemoteImp): ProjectsRemoteImp
}