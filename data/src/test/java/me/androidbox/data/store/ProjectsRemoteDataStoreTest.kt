package me.androidbox.data.store

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import me.androidbox.data.mockdata.ProjectFactory
import me.androidbox.data.repository.ProjectsRemote
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.lang.UnsupportedOperationException

class ProjectsRemoteDataStoreTest {

    private val projectsRemote: ProjectsRemote = mock()
    private lateinit var projectsRemoteDataStore: ProjectsRemoteDataStore

    @Before
    fun setUp() {
        projectsRemoteDataStore = ProjectsRemoteDataStore(projectsRemote)
    }

    @Test
    fun `should get projects`() {
        // Arrange
        val projectEntity = ProjectFactory.makeProjectEntity()
        whenever(projectsRemote.getProjects()).thenReturn(Observable.just(listOf(projectEntity)))

        // Act
        val testObserver = projectsRemoteDataStore.getProjects().test()

        // Assert
        testObserver
            .assertComplete()
            .assertValueCount(1)
            .assertValue(listOf(projectEntity))
        verify(projectsRemote).getProjects()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun `should throw exception when clearing projects`() {
        projectsRemoteDataStore.clearProjects()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun `should throw exception when set projects as booked marked`() {
        projectsRemoteDataStore.setProjectAsBookedmarked("projectId")
    }

    @Test(expected = UnsupportedOperationException::class)
    fun `should throw exception when setting projects as not booked marked`() {
        projectsRemoteDataStore.setProjectAsNotBookedmarked("projectId")
    }
}