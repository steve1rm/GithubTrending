package me.androidbox.mobile.mapper

import me.androidbox.mobile.model.Project
import me.androidbox.presentation.model.ProjectView

interface ViewMapper<in P, out V> {

    fun mapFromView(presentation: ProjectView): Project
}