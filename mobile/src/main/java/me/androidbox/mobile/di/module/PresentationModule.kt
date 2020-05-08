package me.androidbox.mobile.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import me.androidbox.mobile.model.Project
import me.androidbox.presentation.mapper.Mapper
import me.androidbox.presentation.mapper.ProjectViewMapperImp
import me.androidbox.presentation.model.ProjectView
import me.androidbox.presentation.viewModel.BrowseBookmarkedProjectsViewModel
import me.androidbox.presentation.viewModel.BrowseProjectsViewModel
import kotlin.reflect.KClass

@Module
abstract class PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(BrowseProjectsViewModel::class)
    abstract fun bindBrowseProjectsViewModel(viewModel: BrowseProjectsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BrowseBookmarkedProjectsViewModel::class)
    abstract fun bindBrowseBookmarkedProjectsViewModel(viewModel: BrowseBookmarkedProjectsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

   /* TODO find out why cannot use a interface with projectViewMapperImp is a concret implmentation of it
    @Binds
    abstract fun bindProjectViewMapperImp(projectViewMapperImp: ProjectViewMapperImp): Mapper<ProjectView, Project>*/
}

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)
