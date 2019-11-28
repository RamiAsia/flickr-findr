package dev.ramiasia.flickrfindr.data

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.ramiasia.flickrfindr.data.entity.SearchImage
import dev.ramiasia.flickrfindr.data.entity.SearchTerm

/**
 * Data access object used in the database.
 */
@Dao
interface SearchDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(term: SearchTerm)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun bookmark(image: SearchImage)

    @Query("Select * from searchTerms where term like :term||'%'")
    fun getPreviousSearchTerms(term: String): List<SearchTerm>

    @Query("Select * from searchTerms")
    fun getPreviousSearchTerms(): LiveData<List<SearchTerm>>

    @Query("Select * from bookmarks")
    fun getBookmarks(): LiveData<List<SearchImage>>

    @Delete
    fun removeBookmarked(searchImage: SearchImage)

}