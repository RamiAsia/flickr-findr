package dev.ramiasia.bleacherflickort.data.entity

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import dev.ramiasia.bleacherflickort.data.BleacherFlickortDatabase
import dev.ramiasia.bleacherflickort.data.SearchDao
import org.junit.After
import org.junit.Before
import org.junit.Test

class SearchImageReadWriteTest {

    private lateinit var bleacherFlickortDatabase: BleacherFlickortDatabase
    private lateinit var searchDao: SearchDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        bleacherFlickortDatabase =
            Room.inMemoryDatabaseBuilder(context, BleacherFlickortDatabase::class.java).build()
        searchDao = bleacherFlickortDatabase.searchDao()
    }

    @Test
    fun writeSearchImageAndReadFromList() {
        val image = SearchImage("0", "Title", "1100", "dev", "w2e3r4")

        searchDao.bookmark(image)
        assert(searchDao.getBookmarks().value?.get(0) != null)
    }

    @After
    fun finish() {
        bleacherFlickortDatabase.close()
    }

}