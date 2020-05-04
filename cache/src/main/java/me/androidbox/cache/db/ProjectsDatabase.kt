package me.androidbox.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.androidbox.cache.dao.CachedProjectDao
import me.androidbox.cache.dao.ConfigDao
import me.androidbox.cache.model.CacheProject
import me.androidbox.cache.model.Config
import javax.inject.Inject

@Database(entities = [CacheProject::class, Config::class], version = 1)
abstract class ProjectsDatabase @Inject constructor(): RoomDatabase() {

    abstract fun cachedProjectsDao(): CachedProjectDao

    abstract fun configDao(): ConfigDao

    private var INSTANCE: ProjectsDatabase? = null
    private val lock = Any()

    fun getInstance(context: Context): ProjectsDatabase {
        if (INSTANCE == null) {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        ProjectsDatabase::class.java, "projects.db")
                        .build()
                }
                return INSTANCE as ProjectsDatabase
            }
        }
        return INSTANCE as ProjectsDatabase
    }

}