package me.androidbox.domain.interactor

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseUseCase {

    private val compositeDisposable = CompositeDisposable()

    protected fun dispose() {
        if(!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}
