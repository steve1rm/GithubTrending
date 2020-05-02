package me.androidbox.domain.interactor

import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import me.androidbox.domain.executor.PostExecutionThread

abstract class ObservableUseCase<T, in Parameters>(private val postExecutionThread: PostExecutionThread)
    : BaseUseCase() {

    abstract fun buildUseCaseObservable(parameters: Parameters? = null): Observable<T>

    open fun execute(observer: DisposableObserver<T>, parameters: Parameters?) {
        val observable = buildUseCaseObservable(parameters)
            .subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.scheduler)

        addDisposable(observable.subscribeWith(observer))
    }
}
