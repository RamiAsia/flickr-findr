package dev.ramiasia.bleacherflickort.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity containing details about searched terms.
 */
@Entity(tableName = "searchTerms")
data class SearchTerm(
    @PrimaryKey
    val term: String
) {
    override fun equals(other: Any?): Boolean {
        return if (other!!::class == this::class)
            (other as SearchTerm).term == this.term
        else
            super.equals(other)
    }
}