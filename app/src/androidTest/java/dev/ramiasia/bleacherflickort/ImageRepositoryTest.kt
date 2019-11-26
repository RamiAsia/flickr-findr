package dev.ramiasia.bleacherflickort

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import dev.ramiasia.bleacherflickort.utils.TestUtils
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ImageRepositoryTest {

    lateinit var imageRepository: ImageRepository

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        imageRepository = ImageRepository(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun savingSearchTermPersistsInDatabaseAndUpdatesLiveData() {
        //Given an instance of the ImageRepository
        //When a search term is saved and the DB is queried for similar words
        val searchTerm = TestUtils.getTestSearchTerm()
        imageRepository.save(searchTerm.term)
        imageRepository.getPreviouslySearchedTermsLike(searchTerm.term)

        //Then the searchTerms data is updated.
        assertEquals(
            "Search brought incorrect results.",
            imageRepository.searchTerms.getOrAwaitValue()[0],
            searchTerm
        )
    }

    @Test
    fun bookmarkSearchImage_NotifiesObserver() {
        //Given an instance of the ImageRepository
        //When a SearchImage object is bookmarked
        val searchImage = TestUtils.getTestImage()
        imageRepository.bookmark(searchImage)
        //Then the observer should be notified of the new image
        assert(
            imageRepository.bookmarks.getOrAwaitValue()[0].equals(
                searchImage
            )
        )
    }

    @Test
    fun removeBookmarkedImages_UpdatesWithEmptyList() {
        //Given an instance of the ImageRepository
        //When a SearchImage object is bookmarked
        val searchImage = TestUtils.getTestImage()
        imageRepository.bookmark(searchImage)
        //And the bookmark is removed
        imageRepository.removeBookmarked(searchImage)

        //Then the observer should be notified of an empty list
        assert(
            imageRepository.bookmarks.getOrAwaitValue().isEmpty()
        )
    }
}