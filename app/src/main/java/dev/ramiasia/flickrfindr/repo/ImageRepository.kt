package dev.ramiasia.flickrfindr.repo

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.ramiasia.flickrfindr.data.FlickrFindrDatabase
import dev.ramiasia.flickrfindr.data.entity.SearchImage
import dev.ramiasia.flickrfindr.data.entity.SearchTerm
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

/**
 * Repository for obtaining image data.
 */
class ImageRepository(application: Application) {
    private val db = FlickrFindrDatabase.invoke(application)
    private val dao = db.searchDao()

    var bookmarks: LiveData<List<SearchImage>>
        private set
    var searchTerms: MutableLiveData<List<SearchTerm>> = MutableLiveData()
        private set

    init {
        bookmarks = dao.getBookmarks()
    }

    /**
     * Save [SearchTerm] to database for future reference.
     *
     * @param term    Term to save.
     */
    fun save(term: String) {
        CoroutineScope(IO).launch {
            dao.insert(SearchTerm(term))
        }
    }

    /**
     * Obtains a list of searched terms starting with the passed [String]
     *
     * @param term  The term to be searched for.
     */
    fun getPreviouslySearchedTermsLike(term: String) {
        CoroutineScope(IO).launch {
            if (term.isNotBlank()) {
                val terms = dao.getPreviousSearchTerms(term)
                println("Retrieved ${terms.size} terms")
                searchTerms.postValue(terms)
            } else {
                searchTerms.postValue(ArrayList())
            }
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

    /**
     * Removes a [SearchImage] object from the database.
     *
     * @param image Image to be removed from database.
     */
    fun removeBookmarked(image: SearchImage) {
        CoroutineScope(IO).launch {
            dao.removeBookmarked(image)
        }
    }
}