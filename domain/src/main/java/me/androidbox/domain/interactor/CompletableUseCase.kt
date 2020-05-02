package me.androidbox.domain.interactor

import io.reactivex.Completable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import me.androidbox.domain.executor.PostExecutionThread

abstract class CompletableUseCase<in parameters>(private val postExecutionThread: PostExecutionThread)
    : BaseUseCase() {

    abstract fun buildUseCaseCompletable(parameters: parameters): Completable

    open fun execute(observer: DisposableCompletableObserver, parameters: parameters) {
        val completable = buildUseCaseCompletable(parameters)
            .subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.scheduler)

        addDisposable(completable.subscribeWith(observer))
    }

}