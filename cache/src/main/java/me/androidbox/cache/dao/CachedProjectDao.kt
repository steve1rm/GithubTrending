package me.androidbox.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import me.androidbox.cache.db.ProjectConstants.DELETE_PROJECTS
import me.androidbox.cache.db.ProjectConstants.QUERY_BOOKMARKED_PROJECTS
import me.androidbox.cache.db.ProjectConstants.QUERY_PROJECTS
import me.androidbox.cache.db.ProjectConstants.QUERY_UPDATE_BOOKMARK_STATUS
import me.androidbox.cache.model.CacheProject

@Dao
abstract class CachedProjectDao {

    @Query(QUERY_PROJECTS)
    @JvmSuppressWildcards
    abstract fun getProjects(): Observable<List<CacheProject>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun insertProjects(projects: List<CacheProject>): List<Long>

    @Query(DELETE_PROJECTS)
    abstract fun deleteProjects(): Int

    @Query(QUERY_BOOKMARKED_PROJECTS)
    abstract fun getBookmarkedProjects(): Observable<List<CacheProject>>

    @Query(QUERY_UPDATE_BOOKMARK_STATUS)
    abstract fun setBookmarkStatus(isBookMarked: Boolean, projectId: String): Long
}
