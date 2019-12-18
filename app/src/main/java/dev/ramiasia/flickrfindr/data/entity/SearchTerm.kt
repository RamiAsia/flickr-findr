package dev.ramiasia.flickrfindr.data.entity

import io.realm.RealmObject

/**
 * Entity containing details about searched terms.
 */
//@Entity(tableName = "searchTerms")
open class SearchTerm(
//    @PrimaryKey
    var term: String
) : RealmObject() {


    constructor() : this("")

    override fun equals(other: Any?): Boolean {
        return if (other!!::class == this::class)
            (other as SearchTerm).term == this.term
        else
            super.equals(other)
    }

    override fun hashCode(): Int {
        return term.hashCode()
    }
}