package dev.ramiasia.bleacherflickort.ui.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import dev.ramiasia.bleacherflickort.data.entity.SearchImage
import dev.ramiasia.bleacherflickort.network.ImageDataInterface
import dev.ramiasia.bleacherflickort.network.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class SearchViewModel(application: Application) : AndroidViewModel(application) {

    private val API_KEY = "4dfaac69c788c91d83f562e06963b2c4"

    private val ITEM_COUNT = 25

    private var page = 1

    private var lastSearchedTerm = ""

    var images: MutableLiveData<ArrayList<SearchImage>> = MutableLiveData(ArrayList())


    fun getImages(term: String) {
        if (term.isNotEmpty()) {
            val list: ArrayList<SearchImage>?

            if (term != lastSearchedTerm) {
                lastSearchedTerm = term
                list = ArrayList()
                page = 1
            } else {
                list = images.value
            }

            CoroutineScope(IO).launch {
                val imageDataInterface = RetrofitInstance.getRetrofitInstance()
                    .create(ImageDataInterface::class.java)

                val call = imageDataInterface.getImageList(
                    apiKey = API_KEY, itemCount = ITEM_COUNT,
                    term = term, page = page++
                )

                call.photos.photo?.forEach {
                    list?.add(it)
                }
            }
            images.value = list
        }
    }
}
