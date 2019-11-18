package dev.ramiasia.bleacherflickort.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "searchTerms")
data class SearchTerm(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val term: String
)