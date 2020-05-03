package me.androidbox.data.store

import io.reactivex.Completable
import io.reactivex.Observable
import me.androidbox.data.model.ProjectEntity
import me.androidbox.data.repository.ProjectsDataStore
import me.androidbox.data.repository.ProjectsRemote
import javax.inject.Inject

open class ProjectsRemoteDataStore @Inject constructor(
    private val projectsRemote: ProjectsRemote)
    : ProjectsDataStore {

    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectsRemote.getProjects()
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        throw UnsupportedOperationException()
    }

    override fun clearProjects(): Completable {
        throw UnsupportedOperationException()
    }

    override fun getBookedmarkedProjects(): Observable<List<ProjectEntity>> {
        throw UnsupportedOperationException()
    }

    override fun setProjectAsBookedmarked(projectId: String): Completable {
        throw UnsupportedOperationException()
    }

    override fun setProjectAsNotBookedmarked(projectId: String): Completable {
        throw UnsupportedOperationException()
    }
}