package me.androidbox.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.observers.DisposableObserver
import me.androidbox.domain.bookmarked.GetBookedMarkedProjects
import me.androidbox.domain.model.Project
import me.androidbox.presentation.mapper.Mapper
import me.androidbox.presentation.model.ProjectView
import me.androidbox.presentation.state.Resource
import me.androidbox.presentation.state.ResourceState
import javax.inject.Inject

class BrowseBookmarkedProjectsViewModel @Inject constructor(
    private val getBookedMarkedProjects: GetBookedMarkedProjects,
    private val projectViewMapper: Mapper<ProjectView, Project>) /** TODO investigate why the order mappers, compile error when switches round */
    : ViewModel() {

    private val liveData: MutableLiveData<Resource<List<ProjectView>>> = MutableLiveData()

    override fun onCleared() {
        getBookedMarkedProjects.dispose()
        super.onCleared()
    }

    fun getProjects(): LiveData<Resource<List<ProjectView>>> {
        return liveData
    }

    fun fetchProjects() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getBookedMarkedProjects.execute(ProjectSubscriber(), null)
    }

    inner class ProjectSubscriber : DisposableObserver<List<Project>>() {
        override fun onComplete() {}

        override fun onNext(projectList: List<Project>) {
            liveData.postValue(
                Resource(ResourceState.SUCCESS, projectList.map { projectViewMapper.mapToView(it) }, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }
    }
}
