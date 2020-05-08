package me.androidbox.mobile.browse

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_browse.*
import me.androidbox.mobile.R
import me.androidbox.mobile.di.module.ViewModelFactory
import me.androidbox.mobile.mapper.ProjectViewMapperImp
import me.androidbox.mobile.model.Project
import me.androidbox.presentation.model.ProjectView
import me.androidbox.presentation.state.Resource
import me.androidbox.presentation.state.ResourceState
import me.androidbox.presentation.viewModel.BrowseProjectsViewModel
import javax.inject.Inject

class BrowseActivity : AppCompatActivity() {

    @Inject
    lateinit var browseAdapter: BrowseAdapter
    @Inject
    lateinit var projectViewMapperImp: ProjectViewMapperImp
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var browseViewModel: BrowseProjectsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this@BrowseActivity)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //menuInflater.inflate
        return true
    }

    private fun setupBrowseRecycler() {
        browseAdapter.projectListener = projectListener
        rvBrowse.layoutManager = LinearLayoutManager(this)
        rvBrowse.adapter = browseAdapter

    }

    private fun handleDataState(resource: Resource<List<ProjectView>>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {
                setupScreenForSuccess(resource.data?.map {
                    projectViewMapperImp.mapFromView(it)
                })
            }
            ResourceState.LOADING -> {
                progress.visibility = View.VISIBLE
                rvBrowse.visibility = View.GONE
            }
        }
    }

    private fun setupScreenForSuccess(projectList: List<Project>?) {
        progress.visibility = View.GONE

        projectList?.let {
            browseAdapter.projectList = it
            browseAdapter.notifyDataSetChanged()
            rvBrowse.visibility = View.VISIBLE
        } ?: run {

        }
    }

    private val projectListener = object : ProjectListener {
        override fun onBookmarkedProjectClicked(projectId: String) {
            browseViewModel.unBookmarkProject(projectId.toInt())
        }

        override fun onProjectClicked(projectId: String) {
            browseViewModel.bookmarkProject(projectId.toInt())
        }
    }
}
