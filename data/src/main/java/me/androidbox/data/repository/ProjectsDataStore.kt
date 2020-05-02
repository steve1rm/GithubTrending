package me.androidbox.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import me.androidbox.data.model.ProjectEntity

interface ProjectsDataStore {

    fun getProjects(): Observable<List<ProjectEntity>>

    fun saveProjects(projects: List<ProjectEntity>): Completable

    fun clearProjects(): Completable

    fun getBookedmarkedProjects(): Observable<List<ProjectEntity>>

    fun setProjectAsBookedmarked(projectId: String): Completable

    fun setProjectAsNotBookedmarked(projectId: String): Completable
}
