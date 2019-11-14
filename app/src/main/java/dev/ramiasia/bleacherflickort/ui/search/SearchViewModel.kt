package dev.ramiasia.bleacherflickort.ui.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import dev.ramiasia.bleacherflickort.model.entity.SearchImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class SearchViewModel(application: Application) : AndroidViewModel(application) {


    var images: MutableLiveData<ArrayList<SearchImage>> = MutableLiveData(ArrayList())


    fun getImages() {
        val list = if (images.value.isNullOrEmpty()) ArrayList() else images.value
        CoroutineScope(IO).launch {
            for (i in 1..25) {
                list?.add(SearchImage("$i", "url", "Image $i"))
            }
        }
        images.value = list
    }
}
