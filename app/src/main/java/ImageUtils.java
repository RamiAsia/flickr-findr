import android.net.Uri;

import dev.ramiasia.bleacherflickort.data.entity.SearchImage;

public class ImageUtils {

    public static Uri getImageUri(SearchImage image) {
        return Uri.parse("https://farm" + image.farm + ".staticflickr.com/" +
                image.server + "/" + image.id + "_" + image.secret + "_b.jpg");
    }
}
