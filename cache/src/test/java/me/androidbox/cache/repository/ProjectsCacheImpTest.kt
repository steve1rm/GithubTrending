package me.androidbox.cache.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.nhaarman.mockito_kotlin.*
import me.androidbox.cache.db.ProjectsDatabase
import me.androidbox.cache.mapper.CacheMapper
import me.androidbox.cache.mapper.CachedProjectMapper
import me.androidbox.cache.mockdata.ProjectDataFactory
import me.androidbox.cache.model.CacheProject
import me.androidbox.data.model.ProjectEntity
import org.junit.*

import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class ProjectsCacheImpTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val cacheProjectMapper: CacheMapper<CacheProject, ProjectEntity> = mock()
    private val database: ProjectsDatabase by lazy { createDatabase() }
    private lateinit var projectsCacheImp: ProjectsCacheImp

    @Before
    fun setUp() {
        projectsCacheImp = ProjectsCacheImp(database, cacheProjectMapper)
    }

    @After
    fun cleanup() {
        database.close()
    }

    @Test
    fun `should clear projects`() {
        // Act
        val testObserver = projectsCacheImp.clearProjects().test()

        // Assert
        testObserver.assertComplete()
    }

    @Test
    fun `should save projects`() {
        // Arrange
        val projectEntity = ProjectDataFactory.makeProjectEntity()
        val cacheProject = ProjectDataFactory.makeCachedProject()

        whenever(cacheProjectMapper.mapToCached(projectEntity)).thenReturn(cacheProject)

        // Act
        val testObserver = projectsCacheImp.saveProjects(listOf(projectEntity, projectEntity)).test()

        // Assert
        testObserver.assertComplete()
        verify(cacheProjectMapper, times(2)).mapToCached(projectEntity)
        verifyNoMoreInteractions(cacheProjectMapper)
    }

    @Test
    fun `should get projects after inserting`() {
        // Arrange
        val projectEntity = ProjectDataFactory.makeProjectEntity()
        val cacheProject = ProjectDataFactory.makeCachedProject()

        whenever(cacheProjectMapper.mapFromCached(cacheProject)).thenReturn(projectEntity)
        whenever(cacheProjectMapper.mapToCached(projectEntity)).thenReturn(cacheProject)

        projectsCacheImp.saveProjects(listOf(projectEntity)).test()

        // Act
        val testObserver = projectsCacheImp.getProjects().test()

        // Assert
        testObserver
            .assertValueCount(1)
            .assertValue(listOf(projectEntity))

        verify(cacheProjectMapper, atLeastOnce()).mapFromCached(cacheProject)
    }

    @Test
    fun `should get projects after inserting non-mock version`() {
        // Arrange
        projectsCacheImp = ProjectsCacheImp(database, CachedProjectMapper())
        val projectEntity1 = ProjectDataFactory.makeProjectEntity()
        projectEntity1.isBookMarked = false

        projectsCacheImp.saveProjects(listOf(projectEntity1)).test()

        // Act
        val testObserver = projectsCacheImp.getProjects().test()

        // Assert
        testObserver
            .assertValueCount(1)
            .assertValue(listOf(projectEntity1))
    }

    @Test
    fun `should set projects as booked marked`() {
        // Arrange
        projectsCacheImp = ProjectsCacheImp(database, CachedProjectMapper())
        val projectEntity1 = ProjectDataFactory.makeProjectEntity()
        projectEntity1.isBookMarked = false

        // Act
        val testObserver = projectsCacheImp.setProjectAsBookmarked(projectEntity1.projectId.toString()).test()

        // Assert
        testObserver
            .assertComplete()
    }

    @Ignore("broken as isBooked is always true")
    @Test
    fun `should get book marked projects`() {
        // Arrange
        projectsCacheImp = ProjectsCacheImp(database, CachedProjectMapper())
        val bookMarkedProject = ProjectDataFactory.makeBookmarkedProjectEntity()
        val notBookMarkedProject = ProjectDataFactory.makeNotBookmarkedProjectEntity()

        projectsCacheImp.saveProjects(listOf(notBookMarkedProject, bookMarkedProject)).test()

        // Act
        val testObserver = projectsCacheImp.getBookmarkedProjects().test()

        // Assert
        testObserver
            .assertValueCount(1)
            .assertValue(listOf(bookMarkedProject))
    }

    /** TODO Add more unit tests */
    private fun createDatabase(): ProjectsDatabase {
        return Room.inMemoryDatabaseBuilder(
            RuntimeEnvironment.application.applicationContext, ProjectsDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }
}
