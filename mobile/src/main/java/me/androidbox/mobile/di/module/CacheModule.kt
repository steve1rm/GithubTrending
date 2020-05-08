package me.androidbox.mobile.di.module

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import me.androidbox.cache.db.ProjectsDatabase
import me.androidbox.cache.mapper.CacheMapper
import me.androidbox.cache.mapper.CachedProjectMapper
import me.androidbox.cache.model.CacheProject
import me.androidbox.cache.repository.ProjectsCacheImp
import me.androidbox.data.model.ProjectEntity
import me.androidbox.data.repository.ProjectsCache

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
    abstract fun bindProjectCache(projectsCacheImp: ProjectsCacheImp): ProjectsCache

    @Binds
    abstract fun bindCacheMapper(cachedProjectMapper: CachedProjectMapper): CacheMapper<CacheProject, ProjectEntity>
}