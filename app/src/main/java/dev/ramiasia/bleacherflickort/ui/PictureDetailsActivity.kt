package dev.ramiasia.bleacherflickort.ui

import ImageUtils
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import dev.ramiasia.bleacherflickort.R
import dev.ramiasia.bleacherflickort.data.entity.SearchImage

class PictureDetailsActivity : AppCompatActivity() {

    lateinit var imageView: ImageView
    lateinit var titleTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_details)
        init()
    }

    private fun init() {

        val image: SearchImage? = intent.extras?.get(EXTRA_IMAGE) as SearchImage

        image?.let {
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
        item.icon =
            if (!item.isChecked)
                getDrawable(R.drawable.ic_bookmark_empty)
            else
                getDrawable(R.drawable.ic_bookmark_filled)
    }

    companion object {
        const val ACTION_DETAILS = "dev.ramiasia.bleacherflickort.detailsactivity"
        const val EXTRA_IMAGE = "dev.ramiasia.bleacherflickort.detailsactivity.image"
    }
}
