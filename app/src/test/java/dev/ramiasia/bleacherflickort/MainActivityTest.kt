package dev.ramiasia.bleacherflickort

import com.google.android.material.floatingactionbutton.FloatingActionButton
import dev.ramiasia.bleacherflickort.ui.bookmarks.BookmarkFragment
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainActivityTest {

    lateinit var mainActivity: MainActivity
    lateinit var fab: FloatingActionButton

    @Before
    fun setup() {
        mainActivity = Robolectric.buildActivity(MainActivity::class.java)
            .create()
            .resume()
            .get()

        fab = mainActivity.findViewById(R.id.fab)
    }

    @Test
    fun mainActivity_isNotNull() {
        assertNotNull(mainActivity)
    }

    @Test
    fun mainActivity_shouldPresentDefaultFragment() {
        assertNotNull(mainActivity.findViewById(R.id.main_fragment))
    }

    @Test
    fun mainActivity_whenFabPressed_HidesItself() {
        //Given a floating action button
        //When the button is pressed
        fab.performClick()

        //Then the button should hide itself to give way for the new fragment
        assert(fab.isOrWillBeHidden)
    }

    @Test
    fun mainActivity_whenFabPressed_BookmarksShow() {
        //Given a floating action button
        //When the button is pressed
        fab.performClick()

        //Then the Bookmarks fragment displays
        assert(mainActivity.supportFragmentManager.getBackStackEntryAt(0).name == BookmarkFragment::class.java.simpleName)
    }

    @Test
    fun mainActivity_whenReturningToSearchFragment_FabAppears() {
        //Given a floating action button in the MainActivity
        //When the button is pressed and the user is shown the Bookmark fragment
        fab.performClick()

        //And the user returns to the Search fragment
        mainActivity.onBackPressed()

        //Then the button should reveal itself again
        assert(fab.isOrWillBeShown)
    }


}