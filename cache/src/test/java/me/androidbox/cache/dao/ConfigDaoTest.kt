package me.androidbox.cache.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import me.androidbox.cache.db.ProjectsDatabase
import me.androidbox.cache.mockdata.ConfigDataFactory
import me.androidbox.cache.mockdata.DataFactory
import me.androidbox.cache.mockdata.ProjectDataFactory
import me.androidbox.cache.model.Config
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
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

    @Test
    fun `should insert and get config`() {
        // Arrange
        val config = ConfigDataFactory.makeCachedConfig()
        database.configDao().insertConfig(config)

        // Act
        val testObserver = database.configDao().getConfig().test()

        // Assert
        testObserver
            .assertValueCount(1)
            .assertValue(config)
    }
}