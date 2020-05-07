package me.androidbox.mobile

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import me.androidbox.domain.executor.PostExecutionThread
import javax.inject.Inject

class UIThread @Inject constructor() : PostExecutionThread {
    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}
