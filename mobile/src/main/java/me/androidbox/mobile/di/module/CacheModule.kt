package me.androidbox.mobile.di.module

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import me.androidbox.cache.db.ProjectsDatabase
import me.androidbox.cache.repository.ProjectsCacheImp

@Module
abstract class CacheModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideDatabase(application: Application): ProjectsDatabase {
            return ProjectsDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindProjectCache(projectsCacheImp: ProjectsCacheImp): ProjectsCacheImp
}