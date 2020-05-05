package me.androidbox.cache.repository

import android.os.SystemClock
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import me.androidbox.cache.db.ProjectsDatabase
import me.androidbox.cache.mapper.CacheMapper
import me.androidbox.cache.model.CacheProject
import me.androidbox.cache.model.Config
import me.androidbox.data.model.ProjectEntity
import me.androidbox.data.repository.ProjectsCache
import javax.inject.Inject

class ProjectsCacheImp @Inject constructor(
    private val projectsDatabase: ProjectsDatabase,
    private val cachedProjectMapper: CacheMapper<CacheProject, ProjectEntity>)
    : ProjectsCache {

    override fun clearProjects(): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().deleteProjects()
            Completable.complete()
        }
    }

    override fun saveProjects(projectEntityList: List<ProjectEntity>): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().insertProjects(projectEntityList.map { projectEntity ->
                cachedProjectMapper.mapToCached(projectEntity)
            })
            Completable.complete()
        }
    }

    override fun getProjects(): Observable<List<ProjectEntity>> {
        return Observable.defer {
            projectsDatabase.cachedProjectsDao().getProjects()
                .map { cacheProjectList ->
                    cacheProjectList.map { cacheProject ->
                        cachedProjectMapper.mapFromCached(cacheProject) }
                }
        }
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        return Observable.defer {
            projectsDatabase.cachedProjectsDao().getBookmarkedProjects()
                .map { cacheProjectList ->
                    cacheProjectList.map { cacheProject ->
                        cachedProjectMapper.mapFromCached(cacheProject)
                    }
                }
        }
    }

    /** Maybe have one method for called setProjectBookMarkedStatus that passes true/false for marking a book mark
     * instead of having 2 methods */
    override fun setProjectAsBookmarked(projectId: String): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().setBookmarkStatus(isBookMarked = true, projectId = projectId)
            Completable.complete()
        }
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        return Completable.fromCallable {
            projectsDatabase.cachedProjectsDao().setBookmarkStatus(isBookMarked = false, projectId = projectId)
        }
    }

    override fun isProjectCached(): Single<Boolean> {
        return Single.defer {
            projectsDatabase.cachedProjectsDao().getProjects().isEmpty
                .map {
                    !it
                }
        }
    }

    override fun setLastCacheTime(lastCache: Long): Completable {
        return Completable.defer {
            projectsDatabase.configDao().insertConfig(Config(lastCacheTime = SystemClock.currentThreadTimeMillis()))
            Completable.complete()
        }
    }

    override fun isProjectsCacheExpired(): Single<Boolean> {
        val currentTime = System.currentTimeMillis()
        val expirationTime = (60 * 10 * 1000).toLong()

        return projectsDatabase.configDao().getConfig()
            .single(Config(lastCacheTime = 0))
            .map {
                currentTime - it.lastCacheTime > expirationTime
            }
    }
}
