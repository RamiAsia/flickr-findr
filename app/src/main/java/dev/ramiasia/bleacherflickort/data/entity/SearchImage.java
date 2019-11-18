package dev.ramiasia.bleacherflickort.data.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bookmarks")
public class SearchImage {
    @PrimaryKey
    public String id;
    public String title;
    public String farm;
    public String server;
    public String secret;

    public SearchImage() {

    }
}