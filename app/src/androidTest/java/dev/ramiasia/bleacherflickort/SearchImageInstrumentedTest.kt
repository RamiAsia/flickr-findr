package dev.ramiasia.bleacherflickort

import android.os.Parcel
import androidx.test.filters.SmallTest
import dev.ramiasia.bleacherflickort.data.entity.SearchImage
import dev.ramiasia.bleacherflickort.utils.TestUtils
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test

@SmallTest
class SearchImageInstrumentedTest {

    @Test
    fun searchImage_testParcelableCreation() {
        val image = TestUtils.getTestImage()

        val parcel = Parcel.obtain()
        image.writeToParcel(parcel, image.describeContents())
        parcel.setDataPosition(0)

        val imageFromParcel = SearchImage.CREATOR.createFromParcel(parcel)
        Assert.assertThat(imageFromParcel.id, CoreMatchers.`is`(image.id))
        Assert.assertThat(imageFromParcel.title, CoreMatchers.`is`(image.title))
        Assert.assertThat(imageFromParcel.farm, CoreMatchers.`is`(image.farm))
        Assert.assertThat(imageFromParcel.secret, CoreMatchers.`is`(image.secret))
        Assert.assertThat(imageFromParcel.server, CoreMatchers.`is`(image.server))
    }

}