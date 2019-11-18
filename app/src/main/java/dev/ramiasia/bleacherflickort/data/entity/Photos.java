package dev.ramiasia.bleacherflickort.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Photos {
    @SerializedName("photo")
    public List<SearchImage> photo;

    public Photos() {
    }
}