package me.androidbox.data.repository

import io.reactivex.Observable
import me.androidbox.data.model.ProjectEntity

interface ProjectsRemote {

    fun getProjects(): Observable<List<ProjectEntity>>

    fun getBookedmarkedProjects(): Observable<List<ProjectEntity>>
}