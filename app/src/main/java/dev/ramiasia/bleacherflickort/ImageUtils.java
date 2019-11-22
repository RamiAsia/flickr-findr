package dev.ramiasia.bleacherflickort;

import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import dev.ramiasia.bleacherflickort.data.entity.SearchImage;

public class ImageUtils {

    public static void loadImage(SearchImage image, ImageView view, int errorResId,
                                 int placeholderResId) {
        Picasso.get()
                .load(getImageUri(image))
                .placeholder(placeholderResId)
                .error(errorResId)
                .into(view);
    }

    private static Uri getImageUri(SearchImage image) {
        return Uri.parse("https://farm" + image.farm + ".staticflickr.com/" +
                image.server + "/" + image.id + "_" + image.secret + "_b.jpg");
    }
}
