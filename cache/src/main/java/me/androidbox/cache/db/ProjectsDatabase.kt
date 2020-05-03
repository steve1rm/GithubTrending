package me.androidbox.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.androidbox.cache.model.CacheProject
import me.androidbox.cache.model.Config
import javax.inject.Inject

@Database(
    entities = [CacheProject::class, Config::class],
    exportSchema = false,
    version = 1)
abstract class ProjectsDatabase @Inject constructor(context: Context)
    : RoomDatabase() {

    private val projectsDatabase by lazy {
        Room.databaseBuilder(
            context.applicationContext,
            ProjectsDatabase::class.java, "projects.db")
            .build()
    }

    fun getInstance(): ProjectsDatabase = projectsDatabase
}
