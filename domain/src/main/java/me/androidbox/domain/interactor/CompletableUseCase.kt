package me.androidbox.domain.interactor

import io.reactivex.Completable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import me.androidbox.domain.executor.PostExecutionThread

abstract class CompletableUseCase<in Parameters>(private val postExecutionThread: PostExecutionThread)
    : BaseUseCase() {

    /** TODO find a way to remove the nullable parameter and use non-null */
    abstract fun buildUseCaseCompletable(parameters: Parameters?): Completable

    open fun execute(observer: DisposableCompletableObserver, parameters: Parameters? = null) {
        val completable = buildUseCaseCompletable(parameters)
            .subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.scheduler)

        addDisposable(completable.subscribeWith(observer))
    }

}