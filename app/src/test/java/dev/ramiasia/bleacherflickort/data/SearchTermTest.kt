package dev.ramiasia.bleacherflickort.data

import dev.ramiasia.bleacherflickort.utils.TestUtils
import org.junit.Assert
import org.junit.Test

class SearchTermTest {

    @Test
    fun searchTerm_EqualsMethodComparesSameTerm_ReturnsTrue() {
        val termOne = TestUtils.getTestImage()
        val termTwo = TestUtils.getTestImage()

        assert(termOne.equals(termTwo))
    }

    @Test
    fun searchImage_searchTerm_EqualsMethodComparesDifferentObjectType_ReturnsFalse() {
        val image = TestUtils.getTestImage()

        val searchTerm = TestUtils.getTestSearchTerm()

        Assert.assertFalse(searchTerm.equals(image))
    }
}