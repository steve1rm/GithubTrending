package me.androidbox.mobile.bookmarked

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_bookmarked_project.view.*
import me.androidbox.mobile.R
import me.androidbox.mobile.bookmarked.BookmarkedAdapter.ViewHolder
import me.androidbox.mobile.model.Project
import javax.inject.Inject

class BookmarkedAdapter @Inject constructor() : RecyclerView.Adapter<ViewHolder>() {

    var projectList: List<Project> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_bookmarked_project, parent, false))

    override fun getItemCount(): Int = projectList.count()


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val project = projectList[position]

        holder.ownerName.text = project.ownerName
        holder.projectFullname.text = project.fullName

        Glide.with(holder.itemView.context)
            .load(project.ownerAvatar)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.avatarImage)
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val avatarImage: ImageView = view.image_owner_avatar
        val ownerName: TextView = view.text_owner_name
        val projectFullname: TextView = view.text_project_name
    }
}