package dev.ramiasia.flickrfindr.ui.bookmarks;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import dev.ramiasia.flickrfindr.ImageRepository;
import dev.ramiasia.flickrfindr.data.entity.SearchImage;

/**
 * ViewModel class with {@link LiveData} containing bookmarked images.
 */
public class BookmarkViewModel extends AndroidViewModel {

    public LiveData<List<SearchImage>> images;
    private ImageRepository repository;

    public BookmarkViewModel(@NonNull Application application) {
        super(application);

        //Instantiate the repository from which we obtain the images.
        repository = new ImageRepository(application);

        //Set our images to come through the repo's bookmark retreival.
        images = repository.getBookmarks();
    }
}
