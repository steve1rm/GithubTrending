package me.androidbox.data.store

import me.androidbox.data.repository.ProjectsDataStore
import javax.inject.Inject

open class ProjectsDataStoreProvider @Inject constructor(
    private val projectsCacheDataStore: ProjectsCacheDataStore,
    private val projectsRemoteDataStore: ProjectsRemoteDataStore) {

    open fun getDataStore(isProjectCached: Boolean, isCachedExpired: Boolean): ProjectsDataStore {
        return if(isProjectCached && !isCachedExpired) {
            projectsCacheDataStore
        }
        else {
            projectsRemoteDataStore
        }
    }

    open fun getCachedDataStore(): ProjectsDataStore {
        return projectsCacheDataStore
    }
}
