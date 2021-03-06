package me.androidbox.cache.db

object ProjectConstants {

    const val TABLE_NAME_PROJECTS = "projects"

    const val COLUMN_PROJECT_ID = "projectId"

    const val COLUMN_IS_BOOKMARKED = "isBookMarked"

    const val QUERY_PROJECTS = "SELECT * FROM $TABLE_NAME_PROJECTS"

    const val DELETE_PROJECTS = "DELETE FROM $TABLE_NAME_PROJECTS"

    const val QUERY_BOOKMARKED_PROJECTS = "SELECT * FROM $TABLE_NAME_PROJECTS WHERE $COLUMN_IS_BOOKMARKED = 1"

    const val QUERY_UPDATE_BOOKMARK_STATUS = "UPDATE $TABLE_NAME_PROJECTS SET $COLUMN_IS_BOOKMARKED = :isBookMarked WHERE $COLUMN_PROJECT_ID = :projectId"
}
