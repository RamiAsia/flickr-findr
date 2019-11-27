package dev.ramiasia.bleacherflickort

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import dev.ramiasia.bleacherflickort.ui.bookmarks.BookmarkFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Main Activity displayed when the user first opens the application.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTheme(R.style.AppTheme_NoActionBar)

        //Set the listener for interaction with the floating action button
        fab.setOnClickListener {
            //When the FAB is pressed, load the Bookmarks Fragment
            val fragmentManager: FragmentManager = supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.add(R.id.fragment_container, BookmarkFragment.newInstance())
                .addToBackStack(BookmarkFragment::class.java.simpleName)
            transaction.commit()
            //Hide the FAB when loading the Bookmarks Fragment
            fab.hide()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        //Show the FAB again when returning from the Bookmarks Fragment
        fab.show()
    }
}
