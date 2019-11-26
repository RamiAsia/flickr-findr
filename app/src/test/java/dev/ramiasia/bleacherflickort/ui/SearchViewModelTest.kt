package dev.ramiasia.bleacherflickort.ui

import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import dev.ramiasia.bleacherflickort.data.entity.SearchImage
import dev.ramiasia.bleacherflickort.ui.search.SearchViewModel
import dev.ramiasia.bleacherflickort.utils.TestUtils
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SearchViewModelTest {

    private val searchViewModel =
        SearchViewModel(
            getApplicationContext()
        )

    @Mock
    lateinit var observer: Observer<List<SearchImage>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun getImages_ObserverNotifiedOnValueChanged() {
        //When the images are observed:
        searchViewModel.images.observeForever(observer)

        //And a new list of images is posted through the LiveData:
        val newImages = ArrayList<SearchImage>()
        newImages.add(TestUtils.getTestImage())
        searchViewModel.images.postValue(newImages)

        //Then the observer should be notified of the new values:
        verify(observer).onChanged(newImages)
    }

    @Test
    fun getSearchedTerms() {
    }

    @Test
    fun getImages1() {
    }

    @Test
    fun searchTermsLike() {
    }
}