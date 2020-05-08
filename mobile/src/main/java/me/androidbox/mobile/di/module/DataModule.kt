package me.androidbox.mobile.di.module

import dagger.Binds
import dagger.Module
import me.androidbox.data.repository.ProjectsDataRepository

@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataRepository(projectsDataRepository: ProjectsDataRepository): ProjectsDataRepository
}
