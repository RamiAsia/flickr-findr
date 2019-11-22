package dev.ramiasia.bleacherflickort.data

import dev.ramiasia.bleacherflickort.data.entity.SearchImage
import org.junit.Test

class SearchImageTest {

    @Test
    fun searchImage_searchTerm_EqualsMethodComparesSameTerm_ReturnsTrue() {
        val imageOne = SearchImage(
            "0",
            "Title",
            "1100",
            "dev",
            "w2e3r4"
        )

        val imageTwo = SearchImage(
            "0",
            "Title",
            "1100",
            "dev",
            "w2e3r4"
        )

        assert(imageOne.equals(imageTwo))
    }
}