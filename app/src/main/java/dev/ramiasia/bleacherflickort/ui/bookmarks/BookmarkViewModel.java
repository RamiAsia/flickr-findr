package dev.ramiasia.bleacherflickort.ui.bookmarks;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import dev.ramiasia.bleacherflickort.ImageRepository;
import dev.ramiasia.bleacherflickort.data.entity.SearchImage;

public class BookmarkViewModel extends AndroidViewModel {

    public LiveData<List<SearchImage>> images;
    ImageRepository repository;

    public BookmarkViewModel(@NonNull Application application) {
        super(application);
        repository = new ImageRepository(application);
        images = repository.getBookmarks();
    }
}
