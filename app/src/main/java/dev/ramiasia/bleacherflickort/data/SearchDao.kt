package dev.ramiasia.bleacherflickort.data

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.ramiasia.bleacherflickort.data.entity.SearchImage
import dev.ramiasia.bleacherflickort.data.entity.SearchTerm

@Dao
interface SearchDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(term: SearchTerm)

    @Insert
    fun bookmark(image: SearchImage)

    @Query("Select * from searchTerms")
    fun getPreviousSearchTerms(): LiveData<List<SearchTerm>>

    @Query("Select * from bookmarks")
    fun getBookmarks(): LiveData<List<SearchImage>>

    @Delete
    fun removeBookmarked(searchImage: SearchImage)

}