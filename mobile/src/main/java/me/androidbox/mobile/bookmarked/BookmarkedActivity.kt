package me.androidbox.mobile.bookmarked

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_browse.*
import me.androidbox.mobile.R
import me.androidbox.mobile.di.module.ViewModelFactory
import me.androidbox.mobile.mapper.ProjectViewMapperImp
import me.androidbox.presentation.model.ProjectView
import me.androidbox.presentation.state.Resource
import me.androidbox.presentation.state.ResourceState
import me.androidbox.presentation.viewModel.BrowseBookmarkedProjectsViewModel
import javax.inject.Inject

class BookmarkedActivity : AppCompatActivity() {

    @Inject
    lateinit var adapter: BookmarkedAdapter
    @Inject
    lateinit var projectViewMapperImp: ProjectViewMapperImp
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var browseViewModel: BrowseBookmarkedProjectsViewModel

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, BookmarkedActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this@BookmarkedActivity)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_bookmarked_project)

        browseViewModel = ViewModelProviders.of(this@BookmarkedActivity, viewModelFactory)
            .get(BrowseBookmarkedProjectsViewModel::class.java)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupBrowseRecycler()
    }

    override fun onStart() {
        super.onStart()
        browseViewModel.getProjects().observe(this, Observer {
            it?.let {
                handleDataState(it)
            }
        })
        browseViewModel.fetchProjects()
    }

    private fun setupBrowseRecycler() {
        rvBrowse.layoutManager = LinearLayoutManager(this)
        rvBrowse.adapter = adapter
    }

    private fun handleDataState(resource: Resource<List<ProjectView>>) {
        when(resource.status) {
            ResourceState.SUCCESS -> {
                progress.visibility = View.GONE
                rvBrowse.visibility = View.VISIBLE
                resource.data?.let {
                    adapter.projectList = it.map { projectView -> projectViewMapperImp.mapFromView(projectView) }
                    adapter.notifyDataSetChanged()
                }
            }
            ResourceState.LOADING -> {
                progress.visibility = View.VISIBLE
                rvBrowse.visibility = View.GONE
            }
        }
    }
}
