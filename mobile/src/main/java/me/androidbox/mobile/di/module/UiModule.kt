package me.androidbox.mobile.di.module

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.androidbox.domain.executor.PostExecutionThread
import me.androidbox.mobile.UIThread
import me.androidbox.mobile.browse.BrowseActivity

@Module
abstract class UiModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UIThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributedsBrowseActivity(): BrowseActivity
}