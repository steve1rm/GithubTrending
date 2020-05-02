package me.androidbox.data.store

import io.reactivex.Completable
import io.reactivex.Observable
import me.androidbox.data.model.ProjectEntity
import me.androidbox.data.repository.ProjectsCache
import me.androidbox.data.repository.ProjectsDataStore
import javax.inject.Inject

open class ProjectsCacheDataStore @Inject constructor(
    private val projectsCache: ProjectsCache)
    : ProjectsDataStore {

    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectsCache.getProjects()
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return projectsCache.saveProjects(projects)
            .andThen(projectsCache.setLastCacheTime(System.currentTimeMillis()))
    }

    override fun clearProjects(): Completable {
        return projectsCache.clearProjects()
    }

    override fun getBookedmarkedProjects(): Observable<List<ProjectEntity>> {
        return projectsCache.getBookmarkedProjects()
    }

    override fun setProjectAsBookedmarked(projectId: String): Completable {
        return projectsCache.setProjectAsBookmarked(projectId)
    }

    override fun setProjectAsNotBookedmarked(projectId: String): Completable {
        return projectsCache.setProjectAsNotBookmarked(projectId)
    }
}
