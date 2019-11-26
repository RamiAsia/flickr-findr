package dev.ramiasia.bleacherflickort.utils

import dev.ramiasia.bleacherflickort.data.entity.SearchImage
import dev.ramiasia.bleacherflickort.data.entity.SearchTerm

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