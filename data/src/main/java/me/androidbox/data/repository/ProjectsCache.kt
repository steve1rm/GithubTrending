package me.androidbox.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import me.androidbox.data.model.ProjectEntity

interface ProjectsCache {

    fun clearProjects(): Completable

    fun saveProjects(projectEntityList: List<ProjectEntity>): Completable

    fun getProjects(): Observable<List<ProjectEntity>>

    fun getBookmarkedProjects(): Observable<List<ProjectEntity>>

    fun setProjectAsBookmarked(projectId: String): Completable

    fun setProjectAsNotBookmarked(projectId: String): Completable

    fun isProjectCached(): Single<Boolean>

    fun setLastCacheTime(lastCache: Long): Completable

    fun isProjectsCacheExpired(): Single<Boolean>
}

