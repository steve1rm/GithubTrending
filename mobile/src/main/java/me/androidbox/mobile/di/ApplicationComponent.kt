package me.androidbox.mobile.di

import android.app.Application
import android.os.Build
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import me.androidbox.mobile.GithubTrendingApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(githubTrendingApplication: GithubTrendingApplication)
}
