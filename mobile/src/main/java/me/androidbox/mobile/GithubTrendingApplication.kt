package me.androidbox.mobile

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import me.androidbox.mobile.di.DaggerApplicationComponent
import timber.log.Timber
import javax.inject.Inject

class GithubTrendingApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return androidInjector
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
    }
}
