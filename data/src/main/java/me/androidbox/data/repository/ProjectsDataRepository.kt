package me.androidbox.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import me.androidbox.data.mapper.ProjectMapper
import me.androidbox.data.store.ProjectsDataStoreProvider
import me.androidbox.domain.model.Project
import me.androidbox.domain.repository.ProjectsRepository
import javax.inject.Inject

class ProjectsDataRepository @Inject constructor(
    private val projectMapper: ProjectMapper,
    private val projectsDataStoreProvider: ProjectsDataStoreProvider,
    private val projectsCache: ProjectsCache)
    : ProjectsRepository {

    private fun provideDataStore(isCached: Boolean, isCacheExpired: Boolean): Pair<Boolean, Boolean> {
        return Pair(isCached, isCacheExpired)
    }

    override fun getProjects(): Observable<List<Project>> {
        return Observables.zip(
            projectsCache.isProjectCached().toObservable(),
            projectsCache.isProjectsCacheExpired().toObservable(),
            ::provideDataStore)
            .flatMap {
                projectsDataStoreProvider.getDataStore(it.first, it.second)
                    .getProjects()
            }
            .flatMap {
               projectsDataStoreProvider
                   .getCachedDataStore()
                   .saveProjects(it)
                   .andThen(Observable.just(it))
            }
            .map { projectsList ->
                projectsList.map {
                    projectMapper.mapFromEntity(it)
                }
            }
    }

    override fun bookmarkProject(projectId: String): Completable {
        return projectsDataStoreProvider.getCachedDataStore().setProjectAsBookedmarked(projectId)
    }

    override fun unBookmarkProject(projectId: String): Completable {
        return projectsDataStoreProvider.getCachedDataStore().setProjectAsNotBookedmarked(projectId)
    }

    override fun getBookmarkedProjects(): Observable<List<Project>> {
        return projectsDataStoreProvider.getCachedDataStore().getBookedmarkedProjects()
            .map { projectList ->
                projectList.map { projectEntity ->
                    projectMapper.mapFromEntity(projectEntity)
                }
            }
    }
}
