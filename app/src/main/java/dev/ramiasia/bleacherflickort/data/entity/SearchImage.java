package dev.ramiasia.bleacherflickort.data.entity;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bookmarks")
public class SearchImage implements Parcelable {
    public static final Creator<SearchImage> CREATOR = new Creator<SearchImage>() {
        @Override
        public SearchImage createFromParcel(Parcel in) {
            return new SearchImage(in);
        }

        @Override
        public SearchImage[] newArray(int size) {
            return new SearchImage[size];
        }
    };
    public String title;
    public String farm;
    public String server;
    public String secret;
    @PrimaryKey
    @NonNull
    public String id;

    public SearchImage() {

    }

    protected SearchImage(Parcel in) {
        id = in.readString();
        title = in.readString();
        farm = in.readString();
        server = in.readString();
        secret = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(farm);
        dest.writeString(server);
        dest.writeString(secret);
    }
}