package me.androidbox.mobile.browse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_browse.*
import me.androidbox.mobile.R

class BrowseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse)
    }

    private fun setupBrowseRecycler() {
        rvBrowse.layoutManager = LinearLayoutManager(this)

    }
}
