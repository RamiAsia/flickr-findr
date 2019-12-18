package dev.ramiasia.flickrfindr.data.entity;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import io.realm.RealmObject;

/**
 * Entity containing details about searched images.
 */
//@Entity(tableName = "bookmarks")
public class SearchImage extends RealmObject implements Parcelable {
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

    //    @PrimaryKey
    @NonNull
    public String id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFarm() {
        return farm;
    }

    public void setFarm(String farm) {
        this.farm = farm;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public SearchImage() {

    }

    public SearchImage(@NonNull String id, String title, String farm, String server, String secret) {
        this.id = id;
        this.farm = farm;
        this.title = title;
        this.server = server;
        this.secret = secret;
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

    @Override
    public boolean equals(Object o) {
        if (o instanceof SearchImage) {
            SearchImage image = (SearchImage) o;
            return image.id.equals(this.id)
                    && image.farm.equals(this.farm)
                    && image.secret.equals(this.secret)
                    && image.server.equals(this.server)
                    && image.title.equals(this.title);
        }

        return false;
    }
}