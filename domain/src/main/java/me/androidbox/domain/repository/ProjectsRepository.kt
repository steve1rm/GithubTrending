package me.androidbox.domain.repository

import io.reactivex.Completable
import io.reactivex.Observable
import me.androidbox.domain.model.Project

interface ProjectsRepository {
    fun getProjects(): Observable<List<Project>>

    fun bookmarkProject(projectId: String): Completable

    fun unBookmarkProject(projectId: String): Completable

    fun getBookmarkedProjects(): Observable<List<Project>>
}
