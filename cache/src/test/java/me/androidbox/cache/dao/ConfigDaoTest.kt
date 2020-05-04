package me.androidbox.cache.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import me.androidbox.cache.db.ProjectsDatabase
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class ConfigDaoTest {

    @JvmField
    @Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database: ProjectsDatabase by lazy {
        Room.inMemoryDatabaseBuilder(
            RuntimeEnvironment.application.applicationContext,
            ProjectsDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun cleanup() {
        database.close()
    }

}