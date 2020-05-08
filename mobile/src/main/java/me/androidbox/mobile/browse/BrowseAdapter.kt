package me.androidbox.mobile.browse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_project.view.*
import me.androidbox.mobile.R
import me.androidbox.mobile.browse.BrowseAdapter.ViewHolder
import me.androidbox.mobile.model.Project
import javax.inject.Inject

class BrowseAdapter @Inject constructor() : RecyclerView.Adapter<ViewHolder>() {

    var projectList: List<Project> = arrayListOf()
    var projectListener: ProjectListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_project, parent, false))

    override fun getItemCount(): Int = projectList.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val project = projectList[position]

        holder.ownerNameText.text = project.ownerName
        holder.projectNameText.text = project.fullName

        Glide.with(holder.itemView.context)
            .load(project.ownerAvatar)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.avatarImage)

        val starResource = if(project.isBookmarked) {
            R.drawable.ic_star_black_24dp
        }
        else {
            R.drawable.ic_star_border_black_24dp
        }
        holder.bookmarkedImage.setImageResource(starResource)

        holder.itemView.setOnClickListener {
            if(project.isBookmarked) {
                projectListener?.onBookmarkedProjectClicked(project.projectId.toString())
            }
            else {
                projectListener?.onProjectClicked(project.projectId.toString())
            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatarImage: ImageView = view.image_owner_avatar
        val ownerNameText: TextView = view.text_owner_name
        val projectNameText: TextView = view.text_project_name
        val bookmarkedImage: ImageView = view.image_bookmarked
    }
}