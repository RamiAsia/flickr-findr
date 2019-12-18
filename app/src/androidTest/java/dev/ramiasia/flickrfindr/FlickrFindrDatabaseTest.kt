package dev.ramiasia.flickrfindr

//import android.content.Context
//import androidx.room.Room
//import androidx.test.core.app.ApplicationProvider
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import dev.ramiasia.flickrfindr.data.FlickrFindrDatabase
//import dev.ramiasia.flickrfindr.data.SearchDao
//import dev.ramiasia.flickrfindr.utils.TestUtils
//import org.junit.After
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith

//@RunWith(AndroidJUnit4::class)
//class FlickrFindrDatabaseTest {
//
////    private lateinit var flickrFindrDatabase: FlickrFindrDatabase
//    private lateinit var searchDao: SearchDao
//
//    @Before
//    fun setup() {
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        flickrFindrDatabase =
//            Room.inMemoryDatabaseBuilder(context, FlickrFindrDatabase::class.java)
//                .allowMainThreadQueries()
//                .build()
//        searchDao = flickrFindrDatabase.searchDao()
//    }
//
//    @Test
//    fun writeImageAndReadFromList() {
//        val image = TestUtils.getTestImage()
//
//        searchDao.bookmark(image)
//        searchDao.getBookmarks().value?.get(0)?.equals(image)?.let { assert(it) }
//    }
//
//    @Test
//    fun writeSearchTermAndReadFromList() {
//        val searchTerm = TestUtils.getTestSearchTerm()
//        searchDao.insert(searchTerm)
//        assert(searchDao.getPreviousSearchTerms().value?.get(0) != null)
//        searchDao.getPreviousSearchTerms().value?.get(0)?.let { assert(it == searchTerm) }
//    }
//
//    @After
//    fun finish() {
//        flickrFindrDatabase.close()
//    }
//}