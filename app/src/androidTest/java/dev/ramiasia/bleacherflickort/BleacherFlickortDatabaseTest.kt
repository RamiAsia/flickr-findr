package dev.ramiasia.bleacherflickort

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.ramiasia.bleacherflickort.data.BleacherFlickortDatabase
import dev.ramiasia.bleacherflickort.data.SearchDao
import dev.ramiasia.bleacherflickort.utils.TestUtils
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BleacherFlickortDatabaseTest {

    private lateinit var bleacherFlickortDatabase: BleacherFlickortDatabase
    private lateinit var searchDao: SearchDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        bleacherFlickortDatabase =
            Room.inMemoryDatabaseBuilder(context, BleacherFlickortDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        searchDao = bleacherFlickortDatabase.searchDao()
    }

    @Test
    fun writeImageAndReadFromList() {
        val image = TestUtils.getTestImage()

        searchDao.bookmark(image)
        searchDao.getBookmarks().value?.get(0)?.equals(image)?.let { assert(it) }
    }

    @Test
    fun writeSearchTermAndReadFromList() {
        val searchTerm = TestUtils.getTestSearchTerm()
        searchDao.insert(searchTerm)
        assert(searchDao.getPreviousSearchTerms().value?.get(0) != null)
        searchDao.getPreviousSearchTerms().value?.get(0)?.let { assert(it == searchTerm) }
    }

    @After
    fun finish() {
        bleacherFlickortDatabase.close()
    }
}