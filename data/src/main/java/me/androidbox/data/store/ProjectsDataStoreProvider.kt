package me.androidbox.data.store

import me.androidbox.data.repository.ProjectsDataStore
import javax.inject.Inject

class ProjectsDataStoreProvider @Inject constructor(
    private val projectsCacheDataStore: ProjectsCacheDataStore,
    private val projectsRemoteDataStore: ProjectsRemoteDataStore) {

    fun getDataStore(isProjectCached: Boolean, isCachedExpired: Boolean): ProjectsDataStore {
        return if(isProjectCached && !isCachedExpired) {
            projectsCacheDataStore
        }
        else {
            projectsRemoteDataStore
        }
    }

    fun getCachedDataStore(): ProjectsDataStore {
        return projectsCacheDataStore
    }
}
