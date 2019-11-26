package dev.ramiasia.bleacherflickort.data

import dev.ramiasia.bleacherflickort.utils.TestUtils
import org.junit.Assert.assertFalse

import org.junit.Test

class SearchImageTest {

    @Test
    fun searchImage_searchTerm_EqualsMethodComparesSameTerm_ReturnsTrue() {
        val imageOne = TestUtils.getTestImage()

        val imageTwo = TestUtils.getTestImage()

        assert(imageOne.equals(imageTwo))
    }

    @Test
    fun searchImage_searchTerm_EqualsMethodComparesDifferentObjectType_ReturnsFalse() {
        val imageOne = TestUtils.getTestImage()

        val searchTerm = TestUtils.getTestSearchTerm()

        assertFalse(imageOne.equals(searchTerm))
    }

}