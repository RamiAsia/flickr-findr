package dev.ramiasia.bleacherflickort

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import dev.ramiasia.bleacherflickort.ui.bookmarks.BookmarkFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTheme(R.style.AppTheme_NoActionBar)

        fab.setOnClickListener {
            val fragmentManager: FragmentManager = supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.add(R.id.fragment_container, BookmarkFragment.newInstance())
                .addToBackStack(BookmarkFragment::class.simpleName)
            transaction.commit()
            fab.hide()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        fab.show()
    }
}
