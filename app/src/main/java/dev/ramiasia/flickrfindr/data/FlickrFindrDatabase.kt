package dev.ramiasia.flickrfindr.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.ramiasia.flickrfindr.data.entity.SearchImage
import dev.ramiasia.flickrfindr.data.entity.SearchTerm


@Database(
    entities = [SearchImage::class, SearchTerm::class],
    version = 2,
    exportSchema = true
)
abstract class FlickrFindrDatabase : RoomDatabase() {

    abstract fun searchDao(): SearchDao

    companion object {
        @Volatile
        private var instance: FlickrFindrDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, FlickrFindrDatabase::class.java, "bf.db")
                .fallbackToDestructiveMigration()
                .build()
    }

}