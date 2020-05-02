package me.androidbox.domain.interactor

import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import me.androidbox.domain.executor.PostExecutionThread

abstract class SingleUseCase<T, in parameters>(
    private val postExecutionThread: PostExecutionThread)
    : BaseUseCase() {

    protected abstract fun buildUseSingle(parameters: parameters): Single<T>

    open fun execute(observer: DisposableSingleObserver<T>, parameters: parameters) {
        val single = buildUseSingle(parameters)
            .subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.scheduler)

        addDisposable(single.subscribeWith(observer))
    }
}
