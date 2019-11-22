package dev.ramiasia.bleacherflickort.data

import dev.ramiasia.bleacherflickort.data.entity.SearchTerm
import org.junit.Test

class SearchTermTest {

    private val helloWorld = "Hello World!"

    @Test
    fun searchTerm_EqualsMethodComparesSameTerm_ReturnsTrue() {
        val termOne = SearchTerm(helloWorld)
        val termTwo = SearchTerm(helloWorld)

        assert(termOne.equals(termTwo))
    }
}