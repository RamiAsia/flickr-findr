package dev.ramiasia.bleacherflickort.ui.bookmarks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import dev.ramiasia.bleacherflickort.R;
import dev.ramiasia.bleacherflickort.data.entity.SearchImage;
import dev.ramiasia.bleacherflickort.ui.search.SearchImagesRecyclerViewAdapter;

public class BookmarkFragment extends Fragment {

    private BookmarkViewModel viewModel;
    private RecyclerView recyclerView;

    public static BookmarkFragment newInstance() {
        return new BookmarkFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bookmark_fragment, container, false);
        recyclerView = view.findViewById(R.id.bookmarksRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(new SearchImagesRecyclerViewAdapter(getContext()));
        viewModel = ViewModelProviders.of(this).get(BookmarkViewModel.class);
        viewModel = ViewModelProviders.of(this).get(BookmarkViewModel.class);
        viewModel.images.observe(this, new Observer<List<SearchImage>>() {
            @Override
            public void onChanged(List<SearchImage> searchImages) {
                ((SearchImagesRecyclerViewAdapter) recyclerView.getAdapter()).setImages(searchImages);
            }
        });
        return view;
    }

}
