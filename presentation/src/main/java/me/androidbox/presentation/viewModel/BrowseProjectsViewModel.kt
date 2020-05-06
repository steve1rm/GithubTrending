package me.androidbox.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import me.androidbox.domain.bookmarked.BookmarkProject
import me.androidbox.domain.bookmarked.UnbookmarkProject
import me.androidbox.domain.browse.GetProjects
import me.androidbox.domain.model.Project
import me.androidbox.presentation.mapper.Mapper
import me.androidbox.presentation.mapper.ProjectViewMapperImp
import me.androidbox.presentation.model.ProjectView
import me.androidbox.presentation.state.Resource
import me.androidbox.presentation.state.ResourceState
import javax.inject.Inject

class BrowseProjectsViewModel @Inject constructor(
    private val getProjects: GetProjects,
    private val bookmarkProject: BookmarkProject,
    private val unBookmarkProject: UnbookmarkProject,
    private val mapper: Mapper<ProjectView, Project>
)
    : ViewModel() {

    private val liveData: MutableLiveData<Resource<List<ProjectView>>> = MutableLiveData()

    override fun onCleared() {
        getProjects.dispose()
        super.onCleared()
    }

    fun getProjects(): LiveData<Resource<List<ProjectView>>> {
        return liveData
    }

    fun fetchProjects() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getProjects.execute(ProjectsSubscriber())
    }

    fun bookmarkProject(projectId: Int) {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return bookmarkProject.execute(BookmarkProjectSubscriber(), null)
    }

    fun unBookmarkProject(projectId: Int) {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return unBookmarkProject.execute(BookmarkProjectSubscriber())
    }

    inner class ProjectsSubscriber: DisposableObserver<List<Project>>() {
        override fun onComplete() {}

        override fun onNext(t: List<Project>) {
            liveData.postValue(Resource(ResourceState.SUCCESS, t.map { mapper.mapToView(it) }, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }
    }

    /** TODO benefits of using a inner class */
    inner class BookmarkProjectSubscriber : DisposableCompletableObserver() {
        override fun onComplete() {
            liveData.postValue(Resource(ResourceState.SUCCESS, liveData.value?.data, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }
    }
}
