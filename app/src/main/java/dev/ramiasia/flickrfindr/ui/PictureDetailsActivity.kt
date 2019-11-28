package dev.ramiasia.flickrfindr.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dev.ramiasia.flickrfindr.R
import dev.ramiasia.flickrfindr.data.entity.SearchImage
import dev.ramiasia.flickrfindr.repo.ImageRepository
import dev.ramiasia.flickrfindr.utils.ImageUtils
import dev.ramiasia.flickrfindr.viewmodel.BookmarkViewModel

/**
 * Activity displaying the details of a given [SearchImage] object.
 */
class PictureDetailsActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView   //ImageView displaying the image.
    private lateinit var titleTextView: TextView    //Title of the image
    private lateinit var image: SearchImage     //SearchImage object

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_details)
        init()
    }

    private fun init() {
        //Get the image from the intent.
        image = intent.extras?.get(EXTRA_IMAGE) as SearchImage

        //Set the UI elements based on the properties of the image
        image.let {
            supportActionBar?.title = it.title
            imageView = findViewById(R.id.imageDetailView)
            titleTextView = findViewById(R.id.imageDetailTitle)
            titleTextView.text = it.title
            ImageUtils.loadImage(
                it, imageView, R.drawable.ic_launcher_foreground,
                R.drawable.ic_launcher_background
            )
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        //Inflate the options menu containing the bookmark icon
        inflater.inflate(R.menu.image_details_menu, menu)
        //Set the bookmark icon according to whether the image is bookmarked or not.
        val viewModel = ViewModelProviders.of(this).get(BookmarkViewModel::class.java)
        viewModel.images.observe(this, Observer {
            //If the image is bookmarked, make the bookmark icon filled. Icon is outlined otherwise.
            if (it.contains(image)) {
                menu.getItem(0).icon = this.getDrawable(R.drawable.ic_bookmark_filled)
                menu.getItem(0).isChecked = true
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            //When the bookmark icon is pressed,
            R.id.imageDetailsMenu -> {
                item.isChecked = !item.isChecked
                toggleBookmark(item)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * Method bookmarking/unbookmarking [SearchImage] object.
     */
    private fun toggleBookmark(item: MenuItem) {
        val imageRepository =
            ImageRepository(application)
        if (!item.isChecked) {
            item.icon = getDrawable(R.drawable.ic_bookmark_empty)
            imageRepository.removeBookmarked(image)
        } else {
            item.icon = getDrawable(R.drawable.ic_bookmark_filled)
            imageRepository.bookmark(image)
        }
    }

    companion object {
        const val ACTION_DETAILS = "dev.ramiasia.flickrfindr.detailsactivity"
        const val EXTRA_IMAGE = "dev.ramiasia.flickrfindr.detailsactivity.image"
    }
}
