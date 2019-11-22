package dev.ramiasia.bleacherflickort.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dev.ramiasia.bleacherflickort.ImageRepository
import dev.ramiasia.bleacherflickort.ImageUtils
import dev.ramiasia.bleacherflickort.R
import dev.ramiasia.bleacherflickort.data.entity.SearchImage
import dev.ramiasia.bleacherflickort.ui.bookmarks.BookmarkViewModel

class PictureDetailsActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var image: SearchImage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_details)
        init()
    }

    private fun init() {
        image = intent.extras?.get(EXTRA_IMAGE) as SearchImage

        image.let {
            supportActionBar?.title = image.title
            imageView = findViewById(R.id.imageDetailView)
            titleTextView = findViewById(R.id.imageDetailTitle)

            titleTextView.text = image.title
            ImageUtils.loadImage(
                image, imageView, R.drawable.ic_launcher_foreground, R
                    .drawable.ic_launcher_background
            )
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.image_details_menu, menu)
        val viewModel = ViewModelProviders.of(this).get(BookmarkViewModel::class.java)
        viewModel.images.observe(this, Observer {
            if (it.contains(image)) {
                menu.getItem(0).icon = this.getDrawable(R.drawable.ic_bookmark_filled)
                menu.getItem(0).isChecked = true
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.imageDetailsMenu -> {
                item.isChecked = !item.isChecked
                toggleBookmarkIcon(item)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun toggleBookmarkIcon(item: MenuItem) {
        val imageRepository = ImageRepository(application)
        if (!item.isChecked) {
            item.icon = getDrawable(R.drawable.ic_bookmark_empty)
            imageRepository.removeBookmarked(image)
        } else {
            item.icon = getDrawable(R.drawable.ic_bookmark_filled)
            imageRepository.bookmark(image)
        }
    }

    companion object {
        const val ACTION_DETAILS = "dev.ramiasia.bleacherflickort.detailsactivity"
        const val EXTRA_IMAGE = "dev.ramiasia.bleacherflickort.detailsactivity.image"
    }
}
