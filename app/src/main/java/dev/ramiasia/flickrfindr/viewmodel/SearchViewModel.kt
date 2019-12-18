package dev.ramiasia.flickrfindr.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import dev.ramiasia.flickrfindr.data.entity.SearchImage
import dev.ramiasia.flickrfindr.data.entity.SearchTerm
import dev.ramiasia.flickrfindr.network.ImageDataInterface
import dev.ramiasia.flickrfindr.network.RetrofitInstance
import dev.ramiasia.flickrfindr.repo.ImageRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * ViewModel for searching for [SearchImage] and [SearchTerm] objects.
 */
class SearchViewModel(application: Application) : AndroidViewModel(application) {

    private val API_KEY = "1508443e49213ff84d566777dc211f2a"

    private val ITEM_COUNT = 25

    private var page = 1

    private var lastSearchedTerm = ""

    private val imageRepository =
        ImageRepository(application)


    //LiveData holding a list of SearchImages
    var images: MutableLiveData<ArrayList<SearchImage>> = MutableLiveData(ArrayList())
        private set

    //LiveData holding  a list of SearchTerms
    var searchedTerms: MutableLiveData<List<SearchTerm>> = imageRepository.searchTerms
        private set


    /**
     * Method that obtains a list of [SearchImage] objects and updates the [MutableLiveData]
     * holding the corresponding data.
     *
     * @param term  Term being searched for.
     * @param incognito Flag for whether the search term should be remembered and persisted.
     */
    fun getImages(term: String, incognito: Boolean) {
        var incognitoFinal = incognito
        if (term.isNotEmpty()) {
            val list: ArrayList<SearchImage>?
            if (term != lastSearchedTerm) {
                lastSearchedTerm = term
                list = ArrayList()
                page = 1
            } else {
                incognitoFinal = true
                list = images.value
            }

            if (!incognitoFinal) imageRepository.save(term)

            //Launch a coroutine on the IO thread for getting data from the images API
            CoroutineScope(IO).launch {
                val imageDataInterface = RetrofitInstance.getRetrofitInstance()
                    .create(ImageDataInterface::class.java)
                try {
                    val call = imageDataInterface.getImageList(
                        apiKey = API_KEY, itemCount = ITEM_COUNT,
                        term = term, page = page++
                    )

                    call.photos.photo?.forEach {
                        list?.add(it)
                    }
                    //Post the new list to the images LiveData object.
                    images.postValue(list)

                } catch (e: Exception) {
                    when (e) {
                        is UnknownHostException, is SocketTimeoutException -> {
                            println("Error accessing host.")
                        }
                        else -> throw e
                    }
                }


            }
        }
    }

    /**
     * Method for getting terms previously searched similar to a given term.
     *
     * @param term  Term to compare previously searched terms against.
     */
    fun searchTermsLike(term: String) {
        imageRepository.getPreviouslySearchedTermsLike(term)
    }
}
