package dev.ramiasia.bleacherflickort

import android.app.Application
import dev.ramiasia.bleacherflickort.data.BleacherFlickortDatabase
import dev.ramiasia.bleacherflickort.data.entity.SearchImage
import dev.ramiasia.bleacherflickort.data.entity.SearchTerm
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class ImageRepository(application: Application) {
    private val db = BleacherFlickortDatabase.invoke(application)
    private val dao = db.searchDao()

    var bookmarks = dao.getBookmarks()
        private set
    var searchTerms = dao.getPreviousSearchTerms()
        private set

    /**
     * Save [SearchTerm] to database for future reference.
     *
     * @param searchTerm    Term to save.
     */
    fun save(searchTerm: SearchTerm) {
        CoroutineScope(IO).launch {
            dao.insert(searchTerm)
        }
    }

    /**
     * Bookmarks a [SearchImage].
     *
     * @param image Image to bookmark.
     */
    fun bookmark(image: SearchImage) {
        CoroutineScope(IO).launch {
            dao.bookmark(image)
        }
    }
}