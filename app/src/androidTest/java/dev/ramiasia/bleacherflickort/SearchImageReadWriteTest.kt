package dev.ramiasia.bleacherflickort

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import dev.ramiasia.bleacherflickort.data.BleacherFlickortDatabase
import dev.ramiasia.bleacherflickort.data.SearchDao
import dev.ramiasia.bleacherflickort.data.entity.SearchImage
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
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
        val image = SearchImage(
            "0",
            "Title",
            "1100",
            "dev",
            "w2e3r4"
        )

        searchDao.bookmark(image)
        assert(searchDao.getBookmarks().value?.get(0) != null)
    }

    @After
    fun finish() {
        bleacherFlickortDatabase.close()
    }

}