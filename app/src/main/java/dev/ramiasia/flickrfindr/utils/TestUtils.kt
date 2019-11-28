package dev.ramiasia.flickrfindr.utils

import dev.ramiasia.flickrfindr.data.entity.SearchImage
import dev.ramiasia.flickrfindr.data.entity.SearchTerm

/**
 * Class containing methods for cleaner test code.
 */
class TestUtils {
    companion object {
        private val helloWorld = "Hello World!"

        fun getTestImage() = SearchImage(
            "0",
            "Title",
            "1100",
            "dev",
            "w2e3r4"
        )

        fun getTestSearchTerm() = SearchTerm(helloWorld)
    }
}