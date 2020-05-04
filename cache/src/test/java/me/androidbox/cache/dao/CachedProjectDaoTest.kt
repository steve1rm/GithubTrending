package me.androidbox.cache.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import me.androidbox.cache.db.ProjectsDatabase
import me.androidbox.cache.mockdata.ProjectDataFactory
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class CachedProjectDaoTest {

    @Rule
    @JvmField var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database: ProjectsDatabase by lazy {
        Room.inMemoryDatabaseBuilder(RuntimeEnvironment.application.applicationContext,
        ProjectsDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun cleanup() {
        database.close()
    }

    @Test
    fun `should return project data`() {
        // Arrange
        val project = ProjectDataFactory.makeCachedProject()
        database.cachedProjectsDao().insertProjects(listOf(project))

        // Act
        val testObserver = database.cachedProjectsDao().getProjects().test()

        // Assert
        testObserver
            .assertValueCount(1)
            .assertValue(listOf(project))
    }

    @Test
    fun `should delete project data`() {
        // Arrange
        val project = ProjectDataFactory.makeCachedProject()
        database.cachedProjectsDao().insertProjects(listOf(project))
        database.cachedProjectsDao().deleteProjects()

        // Act
        val testObserver = database.cachedProjectsDao().getProjects().test()

        // Assert
        testObserver
            .assertValueCount(1)
            .assertValue(emptyList())
    }


    @Test
    fun `should get book marked projects`() {
        // Arrange
        val project = ProjectDataFactory.makeNotBookMarkedCachedProject()
        val bookMarkedProjects = ProjectDataFactory.makeBookMarkedCachedProject()
        database.cachedProjectsDao().insertProjects(listOf(project, bookMarkedProjects))
        bookMarkedProjects.isBookMarked = true

        // Act
        val testObserver = database.cachedProjectsDao().getBookmarkedProjects().test()

        // Assert
        testObserver
            .assertValueCount(1)
            .assertValue(listOf(bookMarkedProjects))
    }

    @Test
    fun `should set book marked projects`() {
        // Arrange
        val project = ProjectDataFactory.makeBookMarkedCachedProject()
        val bookMarkedProjects = ProjectDataFactory.makeBookMarkedCachedProject()
        database.cachedProjectsDao().insertProjects(listOf(project, bookMarkedProjects))
        database.cachedProjectsDao().setBookmarkStatus(true, project.projectId.toString())
        project.isBookMarked = true
        bookMarkedProjects.isBookMarked = true

        // Act
        val testObserver = database.cachedProjectsDao().getBookmarkedProjects().test()

        // Assert
        testObserver
            .assertValueCount(1)
            .assertValue(listOf(bookMarkedProjects, project))
    }

    @Test
    fun `should set not book marked projects`() {
        // Arrange
        val project = ProjectDataFactory.makeNotBookMarkedCachedProject()
        val bookMarkedProjects = ProjectDataFactory.makeBookMarkedCachedProject()
        database.cachedProjectsDao().insertProjects(listOf(project, bookMarkedProjects))
        database.cachedProjectsDao().setBookmarkStatus(false, project.projectId.toString())
        project.isBookMarked = false

        // Act
        val testObserver = database.cachedProjectsDao().getBookmarkedProjects().test()

        // Assert
        testObserver
            .assertValueCount(1)
            .assertValue(listOf(bookMarkedProjects))
    }
}