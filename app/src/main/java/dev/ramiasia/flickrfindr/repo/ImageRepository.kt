package dev.ramiasia.flickrfindr.repo

//import dev.ramiasia.flickrfindr.data.FlickrFindrDatabase
import android.app.Application
import androidx.lifecycle.MutableLiveData
import dev.ramiasia.flickrfindr.data.entity.SearchImage
import dev.ramiasia.flickrfindr.data.entity.SearchTerm
import io.realm.Realm

/**
 * Repository for obtaining image data.
 */
class ImageRepository(application: Application) {
    var bookmarks: MutableLiveData<List<SearchImage>> = MutableLiveData()
        private set
    var searchTerms: MutableLiveData<List<SearchTerm>> = MutableLiveData()
        private set


    // Get a Realm instance for this thread
    private val realm: Realm = Realm.getDefaultInstance()

    init {
        var bookmarksRealm = realm.where(SearchImage::class.java).findAll()
        bookmarks.postValue(bookmarksRealm.toList())

    }

    /**
     * Save [SearchTerm] to database for future reference.
     *
     * @param term    Term to save.
     */
    fun save(term: String) {
//        CoroutineScope(IO).launch {
        Realm.getDefaultInstance().executeTransactionAsync {
            it.copyToRealm(SearchTerm(term))
        }
//        }
    }

    /**
     * Obtains a list of searched terms starting with the passed [String]
     *
     * @param term  The term to be searched for.
     */
    fun getPreviouslySearchedTermsLike(term: String) {
//        CoroutineScope(IO).launch {
            if (term.isNotBlank()) {
                val terms = Realm.getDefaultInstance().where((SearchTerm::class.java))
                    .beginsWith("term", term).findAll().toList()
                println("Retrieved ${terms.size} terms")
                searchTerms.postValue(terms)
            } else {
                searchTerms.postValue(ArrayList())
            }
//        }

    }

    /**
     * Bookmarks a [SearchImage].
     *
     * @param image Image to bookmark.
     */
    fun bookmark(image: SearchImage) {
//        CoroutineScope(IO).launch {
        Realm.getDefaultInstance().executeTransactionAsync {
            it.copyToRealm(image)
        }
        bookmarks.postValue(Realm.getDefaultInstance().where(SearchImage::class.java).findAll().toList())
//        }
    }

    /**
     * Removes a [SearchImage] object from the database.
     *
     * @param image Image to be removed from database.
     */
    fun removeBookmarked(image: SearchImage) {
//        CoroutineScope(IO).launch {
        Realm.getDefaultInstance().executeTransactionAsync {
            val images = it.where(SearchImage::class.java).equalTo("title", image.title).findAll()
            images.deleteAllFromRealm()
        }
        bookmarks.postValue(Realm.getDefaultInstance().where(SearchImage::class.java).findAll().toList())
//        }
    }
}