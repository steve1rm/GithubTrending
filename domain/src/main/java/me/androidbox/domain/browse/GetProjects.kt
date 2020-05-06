package me.androidbox.domain.browse

import io.reactivex.Observable
import me.androidbox.domain.executor.PostExecutionThread
import me.androidbox.domain.interactor.ObservableUseCase
import me.androidbox.domain.model.Project
import me.androidbox.domain.repository.ProjectsRepository
import javax.inject.Inject

open class GetProjects @Inject constructor(
    private val projectsRepository: ProjectsRepository,
    postExecutionThread: PostExecutionThread)
    : ObservableUseCase<List<Project>, Nothing?>(postExecutionThread) {

    override fun buildUseCaseObservable(parameters: Nothing?): Observable<List<Project>> {
        return projectsRepository.getProjects()
    }

}