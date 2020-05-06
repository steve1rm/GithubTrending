package me.androidbox.domain.bookmarked

import io.reactivex.Completable
import me.androidbox.domain.bookmarked.BookmarkProject.Params
import me.androidbox.domain.executor.PostExecutionThread
import me.androidbox.domain.interactor.CompletableUseCase
import me.androidbox.domain.repository.ProjectsRepository
import java.lang.IllegalArgumentException

open class BookmarkProject(
    private val projectsRepository: ProjectsRepository,
    postExecutionThread: PostExecutionThread)
    : CompletableUseCase<Params>(postExecutionThread) {

    override fun buildUseCaseCompletable(parameters: Params?): Completable {
        if(parameters == null) throw IllegalArgumentException("Parameters cannot be null")

        return projectsRepository.bookmarkProject(parameters.projectId)
    }

    data class Params(val projectId: String) {
        companion object {
            fun forProject(projectId: String): Params {
                return Params(projectId)
            }
        }
    }
}
